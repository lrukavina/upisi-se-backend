package hr.lrukavina.upisisebackend.model.upis;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.common.SifraOpisHelper;
import hr.lrukavina.upisisebackend.common.SifraOpisKolegij;
import hr.lrukavina.upisisebackend.exception.UpisiSeException;
import hr.lrukavina.upisisebackend.exception.VrstaPoruke;
import hr.lrukavina.upisisebackend.model.kolegij.Kolegij;
import hr.lrukavina.upisisebackend.model.kolegij.KolegijManager;
import hr.lrukavina.upisisebackend.model.studij.Studij;
import hr.lrukavina.upisisebackend.model.studij.StudijManager;
import hr.lrukavina.upisisebackend.model.upis.request.AzurUpisRequest;
import hr.lrukavina.upisisebackend.model.upis.request.SpremiUpisRequest;
import hr.lrukavina.upisisebackend.model.upis.response.UpisDto;
import hr.lrukavina.upisisebackend.model.upis.upiskolegij.UpisKolegijManager;
import hr.lrukavina.upisisebackend.model.upisnilist.UpisniList;
import hr.lrukavina.upisisebackend.model.upisnilist.UpisniListManager;
import hr.lrukavina.upisisebackend.model.upisnilist.UpisniListService;
import hr.lrukavina.upisisebackend.model.upisnilist.UpisniStatus;
import hr.lrukavina.upisisebackend.utils.Utils;
import hr.lrukavina.upisisebackend.utils.pdf.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UpisServiceImpl implements UpisService {
  private final UpisManager upisManager;
  private final StudijManager studijManager;
  private final SifraOpisHelper sifraOpisHelper;
  private final KolegijManager kolegijManager;
  private final UpisKolegijManager upisKolegijManager;
  private final UpisniListManager upisniListManager;
  private final UpisniListService upisniListService;
  private final UpisValidator validator;
  private final PdfService pdfService;

  @Override
  public UpisDto dohvati(String sifra) {
    Upis upis = upisManager.dohvati(Utils.desifrirajId(sifra));
    if (upis == null) {
      throw new UpisiSeException(VrstaPoruke.UPIS_NE_POSTOJI_U_BAZI);
    }
    return pripremiUpisDto(upis, dohvatiStatus(upis));
  }

  private UpisniStatus dohvatiStatus(Upis upis) {

    LocalDateTime datumVrijeme = LocalDateTime.now();

    if (datumVrijeme.isBefore(upis.getTstampOd())) {
      return UpisniStatus.NIJE_ZAPOCET;
    }
    if (datumVrijeme.isAfter(upis.getTstampDo())) {
      return UpisniStatus.ZAVRSEN;
    }
    return UpisniStatus.U_TIJEKU;
  }

  private UpisDto pripremiUpisDto(Upis upis, UpisniStatus status) {
    Studij studij = studijManager.dohvati(upis.getStudijId());

    SifraOpis studijSifOpis = sifraOpisHelper.dohvatiStudij(studij);

    SifraOpis visokoUcilisteSifOpis =
        sifraOpisHelper.dohvatiVisokoUciliste(studij.getVisokoUcilisteId());

    List<Kolegij> kolegiji = kolegijManager.dohvatiPoUpisId(upis.getId());

    List<SifraOpisKolegij> obavezniKolegiji =
        kolegiji.stream()
            .filter(Kolegij::isObavezan)
            .map(sifraOpisHelper::dohvatiKolegijUpis)
            .toList();

    List<SifraOpisKolegij> izborniKolegiji =
        kolegiji.stream()
            .filter(kolegij -> !kolegij.isObavezan())
            .map(sifraOpisHelper::dohvatiKolegijUpis)
            .toList();

    return UpisMapper.toDto(
        upis, visokoUcilisteSifOpis, studijSifOpis, obavezniKolegiji, izborniKolegiji, status);
  }

  @Override
  public UpisDto dohvatiAktivneZaKorisnika(String korisnickoIme) {
    Upis upis = upisManager.dohvatiAktivneZaKorisnika(korisnickoIme);
    if (upis == null) {
      return null;
    }
    UpisniStatus status = upisniListManager.dohvatiStatusPoKorisnuku(korisnickoIme);
    return pripremiUpisDto(upis, status);
  }

  @Override
  @Transactional
  public UpisDto spremi(SpremiUpisRequest request) {
    Upis upis = UpisMapper.pripremiSpremanje(request);
    validator.validirajSpremanje(upis);
    upisManager.spremi(upis);

    List<String> kolegijSifre = new ArrayList<>();
    kolegijSifre.addAll(request.getObavezniKolegijiSifre());
    kolegijSifre.addAll(request.getIzborniKolegijiSifre());

    List<Integer> kolegijIdevi = kolegijSifre.stream().map(Utils::desifrirajId).toList();
    upisKolegijManager.spremi(upis.getId(), kolegijIdevi);

    upisniListService.inicijalizirajUpisniList(upis);

    return dohvati(Utils.sifrirajId(upis.getId()));
  }

  @Override
  @Transactional
  public UpisDto azuriraj(AzurUpisRequest request, String sifra) {
    Upis upis = upisManager.dohvati(Utils.desifrirajId(sifra));
    if (upis == null) {
      throw new UpisiSeException(VrstaPoruke.UPIS_NE_POSTOJI_U_BAZI);
    }
    UpisMapper.pripremiZaAzuriranje(request, upis);
    upisManager.azuriraj(upis);

    izbrisiUpisKolegije(request, upis.getId());
    izbrisiUpisniListKolegije(upis.getId());

    return dohvati(Utils.sifrirajId(upis.getId()));
  }

  private void izbrisiUpisKolegije(AzurUpisRequest request, Integer upisId) {
    List<String> kolegijSifre = new ArrayList<>();
    kolegijSifre.addAll(request.getObavezniKolegijiSifre());
    kolegijSifre.addAll(request.getIzborniKolegijiSifre());

    List<Integer> kolegijIdevi = kolegijSifre.stream().map(Utils::desifrirajId).toList();
    upisKolegijManager.azuriraj(upisId, kolegijIdevi);
  }

  private void izbrisiUpisniListKolegije(Integer upisId) {
    List<UpisniList> upisniListovi = upisniListManager.dohvatiPoUpisId(upisId);
    upisniListManager.izbrisi(upisniListovi);
  }

  @Override
  public ByteArrayOutputStream dohvatiPregledUpisa(String korisnickoIme) throws IOException {
    UpisniList upisniList = upisniListManager.dohvatiPoKorisniku(korisnickoIme);
    if (upisniList == null) {
      throw new UpisiSeException(VrstaPoruke.UPISNI_LIST_NE_POSTOJI_U_BAZI);
    }
    return pdfService.generirajPdfPrikaz(upisniList);
  }

  @Override
  public void izbrisi(String sifra) {
    upisManager.izbrisi(Utils.desifrirajId(sifra));
  }
}
