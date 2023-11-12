package hr.lrukavina.upisisebackend.model.upisnilist;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.model.upisnilist.response.UpisniListDto;
import hr.lrukavina.upisisebackend.utils.Konstante;
import hr.lrukavina.upisisebackend.utils.Utils;

import java.text.NumberFormat;

public class UpisniListMapper {
  private UpisniListMapper() {}

  public static UpisniListDto toDto(UpisniList upisniList, SifraOpis korisnik) {
    String cijenaPoEctsu =
        upisniList.getCijenaPoEctsu() != null
            ? NumberFormat.getCurrencyInstance(Konstante.LOCALE_VALUTA)
                .format(upisniList.getCijenaPoEctsu())
            : null;

    String ukupnaCijena =
        upisniList.getUkupnaCijena() != null
            ? NumberFormat.getCurrencyInstance(Konstante.LOCALE_VALUTA)
                .format(upisniList.getUkupnaCijena())
            : null;

    return UpisniListDto.builder()
        .sifra(Utils.sifrirajId(upisniList.getId()))
        .brojEctsa(upisniList.getBrojEctsa())
        .cijenaPoEctsu(cijenaPoEctsu)
        .ukupnaCijena(ukupnaCijena)
        .upisniBroj(upisniList.getUpisniBroj())
        .status(upisniList.getStatus())
        .upisSifra(Utils.sifrirajId(upisniList.getUpisId()))
        .korisnikSifra(Utils.sifrirajId(upisniList.getKorisnikId()))
        .korisnik(korisnik)
        .build();
  }
}
