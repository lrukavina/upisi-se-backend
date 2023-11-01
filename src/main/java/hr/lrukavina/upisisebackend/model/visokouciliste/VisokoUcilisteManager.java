package hr.lrukavina.upisisebackend.model.visokouciliste;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisokoUcilisteManager {
  private final VisokoUcilisteRepository repository;

  VisokoUciliste dohvati(Integer visokoUcilisteId) {
    return repository.dohvati(visokoUcilisteId);
  }

  void spremi(VisokoUciliste visokoUciliste) {
    repository.spremi(visokoUciliste);
  }

  void azuriraj(VisokoUciliste visokoUciliste) {
    repository.azuriraj(visokoUciliste);
  }

  void izbrisi(Integer visokoUcilisteId) {
    repository.izbrisi(visokoUcilisteId);
  }
}
