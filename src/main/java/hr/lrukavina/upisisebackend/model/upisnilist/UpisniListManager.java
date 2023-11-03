package hr.lrukavina.upisisebackend.model.upisnilist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpisniListManager {
  private final UpisniListRepository repository;

  public UpisniList dohvati(Integer upisniListId) {
    return repository.dohvati(upisniListId);
  }

  public UpisniList dohvatiPoKorisnikId(Integer korisnikId) {
    return repository.dohvatiPoKorisnikId(korisnikId);
  }

  public void spremi(UpisniList upisniList) {
    repository.spremi(upisniList);
  }

  public void azuriraj(UpisniList upisniList) {
    repository.azuriraj(upisniList);
  }

  public void izbrisi(Integer upisniListId) {
    repository.izbrisi(upisniListId);
  }
}
