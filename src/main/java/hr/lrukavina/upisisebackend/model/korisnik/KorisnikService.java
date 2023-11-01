package hr.lrukavina.upisisebackend.model.korisnik;

import hr.lrukavina.upisisebackend.model.korisnik.request.AzurirajKorisnikaRequest;
import hr.lrukavina.upisisebackend.model.korisnik.response.KorisnikDto;

public interface KorisnikService {
  KorisnikDto dohvati(String korisnickoIme);

  KorisnikDto azuriraj(AzurirajKorisnikaRequest request, String korisnickoIme);

  void izbrisi(String korisnickoIme);
}
