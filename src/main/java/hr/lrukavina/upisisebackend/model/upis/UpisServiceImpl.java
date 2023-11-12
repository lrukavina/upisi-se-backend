package hr.lrukavina.upisisebackend.model.upis;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.common.SifraOpisHelper;
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
import hr.lrukavina.upisisebackend.model.upisnilist.UpisniListManager;
import hr.lrukavina.upisisebackend.model.upisnilist.UpisniListService;
import hr.lrukavina.upisisebackend.model.upisnilist.UpisniStatus;
import hr.lrukavina.upisisebackend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Override
  public UpisDto dohvati(String sifra) {
    Upis upis = upisManager.dohvati(Utils.desifrirajId(sifra));
    if (upis == null) {
      throw new UpisiSeException(VrstaPoruke.UPIS_NE_POSTOJI_U_BAZI);
    }
    return pripremiUpisDto(upis, null);
  }

  private UpisDto pripremiUpisDto(Upis upis, UpisniStatus status) {
    Studij studij = studijManager.dohvati(upis.getStudijId());

    SifraOpis studijSifOpis = sifraOpisHelper.dohvatiStudij(studij);

    SifraOpis visokoUcilisteSifOpis =
        sifraOpisHelper.dohvatiVisokoUciliste(studij.getVisokoUcilisteId());

    List<Kolegij> kolegiji = kolegijManager.dohvatiPoUpisId(upis.getId());

    List<SifraOpis> obavezniKolegiji =
        kolegiji.stream().filter(Kolegij::isObavezan).map(sifraOpisHelper::dohvatiKolegij).toList();

    List<SifraOpis> izborniKolegiji =
        kolegiji.stream()
            .filter(kolegij -> !kolegij.isObavezan())
            .map(sifraOpisHelper::dohvatiKolegij)
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

    List<String> kolegijSifre = new ArrayList<>();
    kolegijSifre.addAll(request.getObavezniKolegijiSifre());
    kolegijSifre.addAll(request.getIzborniKolegijiSifre());

    List<Integer> kolegijIdevi = kolegijSifre.stream().map(Utils::desifrirajId).toList();
    upisKolegijManager.azuriraj(upis.getId(), kolegijIdevi);

    return dohvati(Utils.sifrirajId(upis.getId()));
  }

  @Override
  public void izbrisi(String sifra) {
    upisManager.izbrisi(Utils.desifrirajId(sifra));
  }
}
