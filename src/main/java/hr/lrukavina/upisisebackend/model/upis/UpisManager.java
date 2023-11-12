package hr.lrukavina.upisisebackend.model.upis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpisManager {
  private final UpisRepository repository;

  public Upis dohvati(Integer upisId) {
    return repository.dohvati(upisId);
  }

  public Upis dohvatiAktivneZaKorisnika(String korisnickoIme) {
    return repository.dohvatiAktivneZaKorisnika(korisnickoIme);
  }

  public void spremi(Upis upis) {
    repository.spremi(upis);
  }

  public void azuriraj(Upis upis) {
    repository.azuriraj(upis);
  }

  public void izbrisi(Integer upisId) {
    repository.izbrisi(upisId);
  }
}
