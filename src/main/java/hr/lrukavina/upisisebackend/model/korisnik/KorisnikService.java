package hr.lrukavina.upisisebackend.model.korisnik;

import hr.lrukavina.upisisebackend.model.korisnik.request.AzurKorisnikaRequest;
import hr.lrukavina.upisisebackend.model.korisnik.response.KorisnikDto;
import hr.lrukavina.upisisebackend.model.korisnik.response.KorisnikInfoDto;

import java.util.List;

public interface KorisnikService {
  List<KorisnikDto> dohvatiSveStudente();

  KorisnikDto dohvati(String korisnickoIme);

  KorisnikInfoDto dohvatiKorisnikInfo(String korisnickoIme);

  KorisnikDto azuriraj(AzurKorisnikaRequest request, String korisnickoIme);

  void izbrisi(String korisnickoIme);

  String generirajKorisnickoIme(String ime, String prezime);
}
