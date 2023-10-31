package hr.lrukavina.upisisebackend.model.korisnik;

import hr.lrukavina.upisisebackend.model.korisnik.request.AzurirajKorisnikaRequest;
import hr.lrukavina.upisisebackend.utils.Utils;
import org.springframework.beans.BeanUtils;

public class KorisnikMapper {
  private KorisnikMapper() {}

  public static void azuriraj(AzurirajKorisnikaRequest request, Korisnik korisnik) {
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
            .visokoUcilisteId(request.getVisokoUcilisteId())
            .build();

    BeanUtils.copyProperties(korisnikRequest, korisnik, Utils.ignoreNullFieldove(korisnikRequest));
  }
}
