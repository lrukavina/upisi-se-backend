package hr.lrukavina.upisisebackend.model.kolegij.kolegijinfo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KolegijInfoManager {
  private final KolegijInfoRepository repository;

  public KolegijInfo dohvati(Integer kolegijInfoId) {
    return repository.dohvati(kolegijInfoId);
  }

  public void spremi(KolegijInfo kolegijInfo) {
    repository.spremi(kolegijInfo);
  }

  public void azuriraj(KolegijInfo kolegijInfo) {
    repository.azuriraj(kolegijInfo);
  }

  public void izbrisi(Integer kolegijInfoId) {
    repository.izbrisi(kolegijInfoId);
  }
}
