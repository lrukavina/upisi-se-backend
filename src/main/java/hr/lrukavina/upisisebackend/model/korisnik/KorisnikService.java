package hr.lrukavina.upisisebackend.model.korisnik;

import hr.lrukavina.upisisebackend.model.korisnik.request.AzurKorisnikaRequest;
import hr.lrukavina.upisisebackend.model.korisnik.response.KorisnikDto;

public interface KorisnikService {
  KorisnikDto dohvati(String korisnickoIme);

  KorisnikDto azuriraj(AzurKorisnikaRequest request, String korisnickoIme);

  void izbrisi(String korisnickoIme);
}
