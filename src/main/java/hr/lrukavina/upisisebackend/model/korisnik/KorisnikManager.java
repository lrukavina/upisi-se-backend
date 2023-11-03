package hr.lrukavina.upisisebackend.model.korisnik;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KorisnikManager {
  private final KorisnikRepository repository;

  public Korisnik dohvati(Integer korisnikId) {
    return repository.dohvati(korisnikId);
  }

  public Korisnik dohvati(String korisnickoIme) {
    return repository.dohvatiPoKorisnickomImenu(korisnickoIme);
  }

  public List<Korisnik> dohvatiPoSemestru(Integer semestar) {
    return repository.dohvatiPoSemestru(semestar);
  }

  public void spremi(Korisnik korisnik) {
    repository.spremi(korisnik);
  }

  public void azuriraj(Korisnik korisnik) {
    repository.azuriraj(korisnik);
  }

  public void izbrisi(String korisnickoIme) {
    repository.izbrisiPoKorisnickomImenu(korisnickoIme);
  }

  public void izbrisi(Integer korisnikId) {
    repository.izbrisi(korisnikId);
  }

  // todo izbrisi sve iz ostalih tablica
}
