package hr.lrukavina.upisisebackend.model.korisnik;

import hr.lrukavina.upisisebackend.model.korisnik.request.AzurKorisnikaRequest;
import hr.lrukavina.upisisebackend.model.korisnik.response.KorisnikDto;
import hr.lrukavina.upisisebackend.model.korisnik.response.KorisnikInfoDto;

public interface KorisnikService {
  KorisnikDto dohvati(String korisnickoIme);

  KorisnikInfoDto dohvatiKorisnikInfo(String korisnickoIme);

  KorisnikDto azuriraj(AzurKorisnikaRequest request, String korisnickoIme);

  void izbrisi(String korisnickoIme);

  String generirajKorisnickoIme(String ime, String prezime);
}
