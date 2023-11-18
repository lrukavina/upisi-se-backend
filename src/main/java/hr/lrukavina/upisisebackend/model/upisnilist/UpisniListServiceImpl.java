package hr.lrukavina.upisisebackend.model.upisnilist;

import hr.lrukavina.upisisebackend.common.SifraOpisHelper;
import hr.lrukavina.upisisebackend.exception.UpisiSeException;
import hr.lrukavina.upisisebackend.exception.VrstaPoruke;
import hr.lrukavina.upisisebackend.model.kolegij.Kolegij;
import hr.lrukavina.upisisebackend.model.kolegij.KolegijManager;
import hr.lrukavina.upisisebackend.model.kolegij.KolegijService;
import hr.lrukavina.upisisebackend.model.kolegij.response.KolegijUpisniListDto;
import hr.lrukavina.upisisebackend.model.korisnik.Korisnik;
import hr.lrukavina.upisisebackend.model.korisnik.KorisnikManager;
import hr.lrukavina.upisisebackend.model.studij.Studij;
import hr.lrukavina.upisisebackend.model.studij.StudijManager;
import hr.lrukavina.upisisebackend.model.upis.Upis;
import hr.lrukavina.upisisebackend.model.upisnilist.request.AzurUpisniListRequest;
import hr.lrukavina.upisisebackend.model.upisnilist.request.PotvrdiUpisniListRequest;
import hr.lrukavina.upisisebackend.model.upisnilist.response.UpisniListDto;
import hr.lrukavina.upisisebackend.model.upisnilist.upisnilistkolegij.UpisniListKolegijManager;
import hr.lrukavina.upisisebackend.utils.Konstante;
import hr.lrukavina.upisisebackend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UpisniListServiceImpl implements UpisniListService {

  private final UpisniListManager upisniListManager;
  private final KolegijManager kolegijManager;
  private final KolegijService kolegijService;
  private final UpisniListKolegijManager upisniListKolegijManager;
  private final SifraOpisHelper sifraOpisHelper;
  private final KorisnikManager korisnikManager;
  private final StudijManager studijManager;
  private final UpisniListValidator validator;

  @Override
  public UpisniListDto dohvati(String sifra) {
    UpisniList upisniList = upisniListManager.dohvati(Utils.desifrirajId(sifra));
    if (upisniList == null) {
      throw new UpisiSeException(VrstaPoruke.UPISNI_LIST_NE_POSTOJI_U_BAZI);
    }

    List<KolegijUpisniListDto> odabraniKolegiji =
        kolegijService.dohvatiPoUpisniListId(upisniList.getId());

    return UpisniListMapper.toDto(
        upisniList, sifraOpisHelper.dohvatiKorisnika(upisniList.getKorisnikId()), odabraniKolegiji);
  }

  @Override
  public UpisniListDto dohvatiPoKorisniku(String korisnickoIme) {
    UpisniList upisniList = upisniListManager.dohvatiPoKorisniku(korisnickoIme);
    if (upisniList == null) {
      throw new UpisiSeException(VrstaPoruke.UPISNI_LIST_NE_POSTOJI_U_BAZI);
    }

    List<KolegijUpisniListDto> odabraniKolegiji =
        kolegijService.dohvatiPoUpisniListId(upisniList.getId());

    return UpisniListMapper.toDto(
        upisniList, sifraOpisHelper.dohvatiKorisnika(upisniList.getKorisnikId()), odabraniKolegiji);
  }

  @Override
  @Transactional
  public UpisniListDto azuriraj(AzurUpisniListRequest request, String korisnickoIme) {
    UpisniList upisniList = upisniListManager.dohvatiPoKorisniku(korisnickoIme);
    if (upisniList == null) {
      throw new UpisiSeException(VrstaPoruke.UPISNI_LIST_NE_POSTOJI_U_BAZI);
    }

    List<Integer> kolegijIdevi =
        request.getKolegijSifre().stream().map(Utils::desifrirajId).toList();

    List<Kolegij> kolegiji = kolegijManager.dohvati(kolegijIdevi);
    validator.validirajOdabraneKolegije(kolegiji, upisniList.getUpisId());

    upisniList.setBrojEctsa(vratiBrojEctsa(kolegiji));
    upisniList.setUkupnaCijena(
        izracunajUkupnuCijenu(upisniList.getBrojEctsa(), upisniList.getCijenaPoEctsu()));
    upisniList.setStatus(UpisniStatus.U_TIJEKU);

    upisniListManager.azuriraj(upisniList);
    upisniListKolegijManager.azuriraj(upisniList.getId(), kolegijIdevi);

    List<KolegijUpisniListDto> odabraniKolegiji =
        kolegijService.dohvatiPoUpisniListId(upisniList.getId());

    return UpisniListMapper.toDto(
        upisniList, sifraOpisHelper.dohvatiKorisnika(upisniList.getKorisnikId()), odabraniKolegiji);
  }

  private Integer vratiBrojEctsa(List<Kolegij> kolegiji) {
    return kolegiji.stream().map(Kolegij::getEcts).reduce(0, Integer::sum);
  }

  private BigDecimal izracunajUkupnuCijenu(Integer brojEctsa, BigDecimal cijenaEctsa) {
    return cijenaEctsa.multiply(BigDecimal.valueOf(brojEctsa));
  }

  @Override
  public UpisniListDto potvrdi(PotvrdiUpisniListRequest request) {
    UpisniList upisniList = upisniListManager.dohvatiPoKorisniku(request.getKorisnickoIme());
    if (upisniList == null) {
      throw new UpisiSeException(VrstaPoruke.UPISNI_LIST_NE_POSTOJI_U_BAZI);
    }
    upisniListManager.potvrdi(upisniList);
    return UpisniListMapper.toDto(
        upisniList,
        sifraOpisHelper.dohvatiKorisnika(upisniList.getKorisnikId()),
        Collections.emptyList());
  }

  @Override
  public void izbrisi(String sifra) {
    upisniListManager.izbrisi(Utils.desifrirajId(sifra));
  }

  @Override
  public void izbrisiPoKorisniku(String korisnickoIme) {
    upisniListManager.izbrisiPoKorisniku(korisnickoIme);
  }

  @Override
  @Transactional
  public void inicijalizirajUpisniList(Upis upis) {
    List<Korisnik> korisnici = korisnikManager.dohvatiPoSemestru(upis.getSemestar());
    Studij studij = studijManager.dohvati(upis.getStudijId());

    for (Korisnik korisnik : korisnici) {
      UpisniList upisniList =
          UpisniList.builder()
              .cijenaPoEctsu(studij.getEctsCijena())
              .upisniBroj(generirajUpisniBroj(upis.getId()))
              .status(UpisniStatus.NIJE_ZAPOCET)
              .upisId(upis.getId())
              .korisnikId(korisnik.getId())
              .build();
      upisniListManager.spremi(upisniList);
    }
  }

  private String generirajUpisniBroj(Integer upisId) {
    return Konstante.UPISNI_BROJ_PREFIX + Konstante.CRTA + String.format("%06d", upisId);
  }
}
