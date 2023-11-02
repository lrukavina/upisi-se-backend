package hr.lrukavina.upisisebackend.model.kolegij;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijinfo.KolegijInfo;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijinfo.request.AzurKolegijInfoRequest;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijinfo.request.SpremiKolegijInfoRequest;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijinfo.response.KolegijInfoDto;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik.KolegijNastavnik;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik.request.AzurKolegijNastavnikRequest;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik.request.SpremiKolegijNastavnikRequest;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik.response.KolegijNastavnikDto;
import hr.lrukavina.upisisebackend.model.kolegij.request.AzurKolegijRequest;
import hr.lrukavina.upisisebackend.model.kolegij.request.SpremiKolegijRequest;
import hr.lrukavina.upisisebackend.model.kolegij.response.KolegijDto;
import hr.lrukavina.upisisebackend.utils.Utils;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class KolegijMapper {
  private KolegijMapper() {}

  public static KolegijDto toDto(
      Kolegij kolegij,
      SifraOpis studij,
      KolegijInfo kolegijInfo,
      List<KolegijNastavnik> kolegijNastavnici,
      SifraOpis kolegijSifOpis) {

    KolegijInfoDto kolegijInfoDto = toKolegijInfoDto(kolegijInfo, kolegijSifOpis);
    List<KolegijNastavnikDto> nastavnici =
        kolegijNastavnici.stream()
            .map(nastavnik -> toKolegijNastavnikDto(nastavnik, kolegijSifOpis))
            .collect(Collectors.toList());

    return KolegijDto.builder()
        .sifra(Utils.sifrirajId(kolegij.getId()))
        .naziv(kolegij.getNaziv())
        .ects(kolegij.getEcts())
        .semestar(kolegij.getSemestar())
        .isvuSifra(kolegij.getIsvuSifra())
        .obavezan(kolegij.isObavezan())
        .studij(studij)
        .kolegijInfo(kolegijInfoDto)
        .nastavnici(nastavnici)
        .build();
  }

  public static KolegijInfoDto toKolegijInfoDto(KolegijInfo kolegijInfo, SifraOpis kolegij) {
    return KolegijInfoDto.builder()
        .sifra(Utils.sifrirajId(kolegijInfo.getId()))
        .informacije(kolegijInfo.getInformacije())
        .kolegij(kolegij)
        .build();
  }

  public static KolegijNastavnikDto toKolegijNastavnikDto(
      KolegijNastavnik kolegijNastavnik, SifraOpis kolegij) {
    return KolegijNastavnikDto.builder()
        .sifra(Utils.sifrirajId(kolegijNastavnik.getId()))
        .ime(kolegijNastavnik.getIme())
        .prezime(kolegijNastavnik.getPrezime())
        .titula(kolegijNastavnik.getTitula())
        .kolegij(kolegij)
        .build();
  }

  public static Kolegij pripremiSpremanjeZaKolegij(SpremiKolegijRequest request) {
    return Kolegij.builder()
        .naziv(request.getNaziv())
        .ects(request.getEcts())
        .semestar(request.getSemestar())
        .isvuSifra(request.getIsvuSifra())
        .obavezan(request.isObavezan())
        .studijId(Utils.desifrirajId(request.getStudijSifra()))
        .build();
  }

  public static KolegijInfo prirpemiSpremanjeZaKolegijInfo(
      SpremiKolegijInfoRequest request, Integer kolegijId) {
    return KolegijInfo.builder().informacije(request.getInformacije()).kolegijId(kolegijId).build();
  }

  public static KolegijNastavnik pripremiSpremanjeZaKolegijNastavnik(
      SpremiKolegijNastavnikRequest request, Integer kolegijId) {
    return KolegijNastavnik.builder()
        .ime(request.getIme())
        .prezime(request.getPrezime())
        .titula(request.getTitula())
        .kolegijId(kolegijId)
        .build();
  }

  public static SpremiKolegijNastavnikRequest azurKolegijNastavnikToSpremiRequest(
      AzurKolegijNastavnikRequest request) {
    return SpremiKolegijNastavnikRequest.builder()
        .ime(request.getIme())
        .prezime(request.getPrezime())
        .titula(request.getTitula())
        .build();
  }

  public static void pripremiKolegijZaAzuriranje(AzurKolegijRequest request, Kolegij kolegij) {
    Kolegij kolegijRequest =
        Kolegij.builder()
            .naziv(request.getNaziv())
            .ects(request.getEcts())
            .semestar(request.getSemestar())
            .isvuSifra(request.getIsvuSifra())
            .obavezan(request.isObavezan())
            .studijId(Utils.desifrirajId(request.getStudijSifra()))
            .build();

    BeanUtils.copyProperties(kolegijRequest, kolegij, Utils.ignoreNullFieldove(kolegijRequest));
  }

  public static void pripremiKolegijInfoZaAzuriranje(
      AzurKolegijInfoRequest request, KolegijInfo kolegijInfo) {
    KolegijInfo kolegijInfoRequest =
        KolegijInfo.builder()
            .informacije(request.getInformacije())
            .kolegijId(Utils.desifrirajId(request.getKolegijSifra()))
            .build();

    BeanUtils.copyProperties(
        kolegijInfoRequest, kolegijInfo, Utils.ignoreNullFieldove(kolegijInfoRequest));
  }

  public static void pripremiKolegijNastavnikZaAzuriranje(
      AzurKolegijNastavnikRequest request, KolegijNastavnik kolegijNastavnik) {
    KolegijNastavnik kolegijNastavnikRequest =
        KolegijNastavnik.builder()
            .ime(request.getIme())
            .prezime(request.getPrezime())
            .titula(request.getTitula())
            .kolegijId(Utils.desifrirajId(request.getKolegijSifra()))
            .build();

    BeanUtils.copyProperties(
        kolegijNastavnikRequest,
        kolegijNastavnik,
        Utils.ignoreNullFieldove(kolegijNastavnikRequest));
  }
}
