package hr.lrukavina.upisisebackend.model.korisnik;

import hr.lrukavina.upisisebackend.exception.UpisiSeException;
import hr.lrukavina.upisisebackend.exception.VrstaPoruke;
import hr.lrukavina.upisisebackend.model.korisnik.request.AzurirajKorisnikaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KorisnikServiceImpl implements KorisnikService {

  private final KorisnikManager manager;

  @Override
  public Korisnik dohvatiPoKorisnickomImenu(String korisnickoIme) {
    return null;
  }

  @Override
  @Transactional
  public Korisnik azuriraj(AzurirajKorisnikaRequest request, String korisnickoIme) {
    Korisnik korisnik = manager.dohvati(korisnickoIme);
    if (korisnik == null) {
      throw new UpisiSeException(VrstaPoruke.KORISNIK_NE_POSTOJI_U_BAZI);
    }
    KorisnikMapper.azuriraj(request, korisnik);
    manager.azuriraj(korisnik);
    return korisnik;
  }

  @Override
  public void izbrisi(String korisnickoIme) {
    manager.izbrisi(korisnickoIme);
  }
}
