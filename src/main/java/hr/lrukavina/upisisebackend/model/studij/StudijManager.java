package hr.lrukavina.upisisebackend.model.studij;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudijManager {
  private final StudijRepository repository;

  public List<Studij> dohvatiSve() {
    return repository.dohvatiSve();
  }

  public Studij dohvati(Integer studijId) {
    return repository.dohvati(studijId);
  }

  public Studij dohvatiPoUpisniListId(Integer upisniListId) {
    return repository.dohvatiPoUpisniListId(upisniListId);
  }

  public List<Studij> dohvatiPoVisokoUcilisteId(Integer visokoUcilisteId) {
    return repository.dohvatiPoVisokoUcilisteId(visokoUcilisteId);
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
