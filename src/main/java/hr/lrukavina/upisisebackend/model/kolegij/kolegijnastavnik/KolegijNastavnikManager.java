package hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KolegijNastavnikManager {
  private final KolegijNastavnikRepository repository;

  public KolegijNastavnik dohvati(Integer kolegijNastavnikId) {
    return repository.dohvati(kolegijNastavnikId);
  }

  public void spremi(KolegijNastavnik kolegijNastavnik) {
    repository.spremi(kolegijNastavnik);
  }

  public void azuriraj(KolegijNastavnik kolegijNastavnik) {
    repository.azuriraj(kolegijNastavnik);
  }

  public void izbrisi(Integer kolegijNastavnikId) {
    repository.izbrisi(kolegijNastavnikId);
  }
}
