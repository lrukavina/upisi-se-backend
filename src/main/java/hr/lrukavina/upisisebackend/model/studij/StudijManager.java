package hr.lrukavina.upisisebackend.model.studij;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudijManager {
  private final StudijRepository repository;

  public Studij dohvati(Integer studijId) {
    return repository.dohvati(studijId);
  }

  public Studij dohvatiPoUpisniListId(Integer upisniListId) {
    return repository.dohvatiPoUpisniListId(upisniListId);
  }

  public void spremi(Studij studij) {
    repository.spremi(studij);
  }

  public void azuriraj(Studij studij) {
    repository.azuriraj(studij);
  }

  public void izbrisi(Integer studijId) {
    repository.izbrisi(studijId);
  }
}
