package hr.lrukavina.upisisebackend.model.upisnilist;

import hr.lrukavina.upisisebackend.exception.UpisiSeException;
import hr.lrukavina.upisisebackend.exception.VrstaPoruke;
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

  public UpisniStatus dohvatiStatusPoKorisnuku(String korisnickoIme) {
    UpisniList upisniList = repository.dohvatiPoKorisniku(korisnickoIme);
    if (upisniList == null) {
      throw new UpisiSeException(VrstaPoruke.UPISNI_LIST_NE_POSTOJI_U_BAZI);
    }
    return upisniList.getStatus();
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
