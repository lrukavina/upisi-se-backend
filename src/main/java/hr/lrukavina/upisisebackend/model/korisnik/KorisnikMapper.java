package hr.lrukavina.upisisebackend.model.korisnik;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.model.korisnik.request.AzurKorisnikaRequest;
import hr.lrukavina.upisisebackend.model.korisnik.response.KorisnikDto;
import hr.lrukavina.upisisebackend.utils.Utils;
import org.springframework.beans.BeanUtils;

public class KorisnikMapper {
  private KorisnikMapper() {}

  public static void pripremiZaAzuriranje(AzurKorisnikaRequest request, Korisnik korisnik) {
    Korisnik korisnikRequest =
        Korisnik.builder()
            .ime(request.getIme())
            .prezime(request.getPrezime())
            .jmbag(request.getJmbag())
            .adresa(request.getAdresa())
            .email(request.getEmail())
            .korisnickoIme(request.getKorisnickoIme())
            .lozinka(request.getLozinka())
            .rola(request.getRola())
            .visokoUcilisteId(Utils.desifrirajId(request.getVisokoUcilisteSifra()))
            .build();

    BeanUtils.copyProperties(korisnikRequest, korisnik, Utils.ignoreNullFieldove(korisnikRequest));
  }

  public static KorisnikDto toDto(Korisnik korisnik, SifraOpis visokoUciliste) {
    return KorisnikDto.builder()
        .ime(korisnik.getIme())
        .prezime(korisnik.getPrezime())
        .jmbag(korisnik.getJmbag())
        .adresa(korisnik.getJmbag())
        .rola(korisnik.getRola())
        .visokoUciliste(visokoUciliste)
        .build();
  }
}
