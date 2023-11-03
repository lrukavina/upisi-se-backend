package hr.lrukavina.upisisebackend.model.upisnilist.upisnilistkolegij;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpisniListKolegijManager {
  private final UpisniListKolegijRepository repository;

  public void spremi(Integer upisniListId, Integer kolegijId) {
    repository.spremi(upisniListId, kolegijId);
  }

  public void spremi(Integer upisniListId, List<Integer> kolegijIdevi) {
    for (Integer kolegijId : kolegijIdevi) {
      repository.spremi(upisniListId, kolegijId);
    }
  }

  public void azuriraj(Integer upisniListId, Integer kolegijId) {
    repository.izbrisi(upisniListId);
    repository.spremi(upisniListId, kolegijId);
  }

  public void azuriraj(Integer upisniListId, List<Integer> kolegijIdevi) {
    // todo potrebno obrisati (vratiti u status NIJE_ZAPOCET)  upisni list i izbirsati veznu tablicu
    // upisni_list_kolegij
    repository.izbrisi(upisniListId);
    for (Integer kolegijId : kolegijIdevi) {
      repository.spremi(upisniListId, kolegijId);
    }
  }
}
