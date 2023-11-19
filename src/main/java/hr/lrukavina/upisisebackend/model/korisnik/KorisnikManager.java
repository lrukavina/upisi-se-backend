package hr.lrukavina.upisisebackend.model.korisnik;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KorisnikManager {
  private final KorisnikRepository repository;
  private static final String LIKE_WILDCARD = "%";

  public List<Korisnik> dohvatiSveStudente() {
    return repository.dohvatiSveStudente();
  }

  public Korisnik dohvati(Integer korisnikId) {
    return repository.dohvati(korisnikId);
  }

  public Korisnik dohvati(String korisnickoIme) {
    return repository.dohvatiPoKorisnickomImenu(korisnickoIme);
  }

  public Korisnik dohvatiZadnjeg(String korisnickoIme) {
    korisnickoIme = LIKE_WILDCARD + korisnickoIme + LIKE_WILDCARD;
    return repository.dohvatiZadnjeg(korisnickoIme);
  }

  public Korisnik dohvatiPoUpisniListId(Integer upisniListId) {
    return repository.dohvatiPoUpisniListId(upisniListId);
  }

  public List<Korisnik> dohvatiPoUpisId(Integer upisId) {
    return repository.dohvatiPoUpisId(upisId);
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
}
