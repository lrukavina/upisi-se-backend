package hr.lrukavina.upisisebackend.model.upis.upiskolegij;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpisKolegijManager {
  private final UpisKolegijRepository repository;

  public void spremi(Integer upisId, Integer kolegijId) {
    repository.spremi(upisId, kolegijId);
  }

  public void spremi(Integer upisId, List<Integer> kolegijIdevi) {
    for (Integer kolegijId : kolegijIdevi) {
      repository.spremi(upisId, kolegijId);
    }
  }

  public void azuriraj(Integer upisId, Integer kolegijId) {
    repository.izbrisi(upisId);
    repository.spremi(upisId, kolegijId);
  }

  public void azuriraj(Integer upisId, List<Integer> kolegijIdevi) {
    // todo potrebno obrisati (vratiti u status NIJE_ZAPOCET)  upisni list i izbirsati veznu tablicu
    // upisni_list_kolegij
    repository.izbrisi(upisId);
    for (Integer kolegijId : kolegijIdevi) {
      repository.spremi(upisId, kolegijId);
    }
  }
}
