package hr.lrukavina.upisisebackend.model.kolegij;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KolegijManager {
  private final KolegijRepository repository;

  public Kolegij dohvati(Integer kolegijId) {
    return repository.dohvati(kolegijId);
  }

  public void spremi(Kolegij kolegij) {
    repository.spremi(kolegij);
  }

  public void azuriraj(Kolegij kolegij) {
    repository.azuriraj(kolegij);
  }

  public void izbrisi(Integer kolegijId) {
    repository.izbrisi(kolegijId);
  }
}
