package hr.lrukavina.upisisebackend.model.korisnik;

import hr.lrukavina.upisisebackend.model.korisnik.request.AzurirajKorisnikaRequest;

public interface KorisnikService {
  // todo vratiti DTO za kontroler
  Korisnik dohvatiPoKorisnickomImenu(String korisnickoIme);

  Korisnik azuriraj(AzurirajKorisnikaRequest request, String korisnickoIme);

  void izbrisi(String korisnickoIme);
}
