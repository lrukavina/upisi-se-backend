package hr.lrukavina.upisisebackend.model.kolegij;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KolegijManager {
  private final KolegijRepository repository;

  public Kolegij dohvati(Integer kolegijId) {
    return repository.dohvati(kolegijId);
  }

  public List<Kolegij> dohvatiSve() {
    return repository.dohvatiSve();
  }

  public List<Kolegij> dohvati(List<Integer> kolegijIdevi) {
    List<Kolegij> kolegiji = new ArrayList<>();
    for (Integer kolegijId : kolegijIdevi) {
      kolegiji.add(repository.dohvati(kolegijId));
    }
    return kolegiji;
  }

  public List<Kolegij> dohvatiPoUpisId(Integer upisId) {
    return repository.dohvatiPoUpisId(upisId);
  }

  public List<Kolegij> dohvatiPoUpisniListId(Integer upisniListId) {
    return repository.dohvatiPoUpisniListId(upisniListId);
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
