package hr.lrukavina.upisisebackend.model.upis.upisInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpisInfoManager {
  private final UpisInfoRepository repository;

  public UpisInfo dohvati(Integer upisInfoId) {
    return repository.dohvati(upisInfoId);
  }

  public void spremi(UpisInfo upisInfo) {
    repository.spremi(upisInfo);
  }

  public void azuriraj(UpisInfo upisInfo) {
    repository.azuriraj(upisInfo);
  }

  public void izbrisi(Integer upisInfoId) {
    repository.izbrisi(upisInfoId);
  }
}
