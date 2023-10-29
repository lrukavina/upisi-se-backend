package hr.lrukavina.upisisebackend.model.korisnik;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KorisnikManager {
  private final KorisnikRepository repository;

  public Korisnik dohvatiPoKorisnickomImenu(String korisnickoIme) {
    return repository.dohvatiPoKorisnickomImenu(korisnickoIme);
  }

  public void spremi(Korisnik korisnik) {
    repository.spremi(korisnik);
  }
}
