package hr.lrukavina.upisisebackend.model.visokouciliste;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisokoUcilisteManager {
  private final VisokoUcilisteRepository repository;

  public VisokoUciliste dohvati(Integer visokoUcilisteId) {
    return repository.dohvati(visokoUcilisteId);
  }

  public List<VisokoUciliste> dohvatiSve() {
    return repository.dohvatiSve();
  }

  public VisokoUciliste dohvatiPoUpisniListId(Integer upisniListId) {
    return repository.dohvatiPoUpisniListId(upisniListId);
  }

  public void spremi(VisokoUciliste visokoUciliste) {
    repository.spremi(visokoUciliste);
  }

  public void azuriraj(VisokoUciliste visokoUciliste) {
    repository.azuriraj(visokoUciliste);
  }

  public void izbrisi(Integer visokoUcilisteId) {
    repository.izbrisi(visokoUcilisteId);
  }
}
