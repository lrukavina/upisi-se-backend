package hr.lrukavina.upisisebackend.model.ugovor;

import hr.lrukavina.upisisebackend.model.korisnik.Korisnik;
import hr.lrukavina.upisisebackend.model.studij.Studij;
import hr.lrukavina.upisisebackend.model.ugovor.dto.UgovorKolegijDto;
import hr.lrukavina.upisisebackend.model.visokouciliste.VisokoUciliste;
import hr.lrukavina.upisisebackend.utils.Konstante;

import java.util.List;

public class UgovorMapper {
  private UgovorMapper() {}

  public static Ugovor toUgovor(
      Studij studij,
      Korisnik korisnik,
      VisokoUciliste visokoUciliste,
      List<UgovorKolegijDto> kolegiji,
      String klasa,
      String urbroj) {

    String pozivNaBroj = generirajPozivNaBroj(korisnik.getJmbag());

    return Ugovor.builder()
        .nazivStudija(studij.getNazivStudija())
        .nazivSmjera(studij.getNazivSmjera())
        .klasa(klasa)
        .urbroj(urbroj)
        .ime(korisnik.getIme())
        .prezime(korisnik.getPrezime())
        .jmbag(korisnik.getJmbag())
        .semestar(korisnik.getSemestar())
        .kolegiji(kolegiji)
        .iban(visokoUciliste.getIban())
        .pozivNaBroj(pozivNaBroj)
        .build();
  }

  private static String generirajPozivNaBroj(String jmbag) {
    return Konstante.UPISNI_BROJ_PREFIX
        + Konstante.CRTA
        + jmbag
        + Konstante.CRTA
        + Konstante.POZIV_NA_BROJ_SUFIX;
  }
}
