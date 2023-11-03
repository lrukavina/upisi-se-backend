package hr.lrukavina.upisisebackend.model.upisnilist;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.model.studij.Studij;
import hr.lrukavina.upisisebackend.model.studij.request.AzurStudijRequest;
import hr.lrukavina.upisisebackend.model.studij.request.SpremiStudijRequest;
import hr.lrukavina.upisisebackend.model.upisnilist.request.AzurUpisniListRequest;
import hr.lrukavina.upisisebackend.model.upisnilist.response.UpisniListDto;
import hr.lrukavina.upisisebackend.utils.Konstante;
import hr.lrukavina.upisisebackend.utils.Utils;
import org.springframework.beans.BeanUtils;

import java.text.NumberFormat;

public class UpisniListMapper {
  private UpisniListMapper() {}

  public static UpisniListDto toDto(UpisniList upisniList, SifraOpis korisnik) {
    return UpisniListDto.builder()
        .sifra(Utils.sifrirajId(upisniList.getId()))
        .brojEctsa(upisniList.getBrojEctsa())
        .cijenaPoEctsu(
            NumberFormat.getCurrencyInstance(Konstante.LOCALE_VALUTA)
                .format(upisniList.getCijenaPoEctsu()))
        .ukupnaCijena(
            NumberFormat.getCurrencyInstance(Konstante.LOCALE_VALUTA)
                .format(upisniList.getUkupnaCijena()))
        .upisniBroj(upisniList.getUpisniBroj())
        .status(upisniList.getStatus())
        .upisSifra(Utils.sifrirajId(upisniList.getUpisId()))
        .korisnikSifra(Utils.sifrirajId(upisniList.getKorisnikId()))
        .korisnik(korisnik)
        .build();
  }
}
