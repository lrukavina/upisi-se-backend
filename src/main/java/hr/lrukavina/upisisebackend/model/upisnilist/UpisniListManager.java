package hr.lrukavina.upisisebackend.model.upisnilist;

import hr.lrukavina.upisisebackend.exception.UpisiSeException;
import hr.lrukavina.upisisebackend.exception.VrstaPoruke;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpisniListManager {
  private final UpisniListRepository repository;

  public UpisniList dohvati(Integer upisniListId) {
    return repository.dohvati(upisniListId);
  }

  public UpisniList dohvatiPoKorisniku(String korisnickoIme) {
    return repository.dohvatiPoKorisniku(korisnickoIme);
  }

  public UpisniStatus dohvatiStatusPoKorisnuku(String korisnickoIme) {
    UpisniList upisniList = repository.dohvatiPoKorisniku(korisnickoIme);
    if (upisniList == null) {
      throw new UpisiSeException(VrstaPoruke.UPISNI_LIST_NE_POSTOJI_U_BAZI);
    }
    return upisniList.getStatus();
  }

  public List<UpisniList> dohvatiPoUpisId(Integer upisId) {
    return repository.dohvatiPoUpisId(upisId);
  }

  public void spremi(UpisniList upisniList) {
    repository.spremi(upisniList);
  }

  public void azuriraj(UpisniList upisniList) {
    repository.azuriraj(upisniList);
  }

  public void potvrdi(UpisniList upisniList) {
    repository.potvrdi(upisniList);
  }

  public void izbrisi(Integer upisniListId) {
    repository.izbrisi(upisniListId);
  }

  public void izbrisi(List<UpisniList> upisniListovi) {
    for (UpisniList upisniList : upisniListovi) {
      repository.izbrisi(upisniList.getId());
    }
  }

  public void izbrisiPoKorisniku(String korisnickoIme) {
    UpisniList upisniList = dohvatiPoKorisniku(korisnickoIme);
    if (upisniList == null) {
      return;
    }
    repository.izbrisi(upisniList.getId());
  }
}
