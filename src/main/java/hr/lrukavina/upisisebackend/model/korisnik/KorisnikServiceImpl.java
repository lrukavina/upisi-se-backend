package hr.lrukavina.upisisebackend.model.korisnik;

import hr.lrukavina.upisisebackend.common.SifraOpisHelper;
import hr.lrukavina.upisisebackend.exception.UpisiSeException;
import hr.lrukavina.upisisebackend.exception.VrstaPoruke;
import hr.lrukavina.upisisebackend.model.korisnik.request.AzurKorisnikaRequest;
import hr.lrukavina.upisisebackend.model.korisnik.response.KorisnikDto;
import hr.lrukavina.upisisebackend.model.korisnik.response.KorisnikInfoDto;
import hr.lrukavina.upisisebackend.model.studij.Studij;
import hr.lrukavina.upisisebackend.model.studij.StudijManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KorisnikServiceImpl implements KorisnikService {

  private final KorisnikManager manager;
  private final StudijManager studijManager;
  private final SifraOpisHelper sifraOpisHelper;

  @Override
  public KorisnikDto dohvati(String korisnickoIme) {
    Korisnik korisnik = manager.dohvati(korisnickoIme);
    if (korisnik == null) {
      throw new UpisiSeException(VrstaPoruke.KORISNIK_NE_POSTOJI_U_BAZI);
    }
    return KorisnikMapper.toDto(
        korisnik,
        sifraOpisHelper.dohvatiVisokoUciliste(korisnik.getVisokoUcilisteId()),
        sifraOpisHelper.dohvatiStudij(korisnik.getStudijId()));
  }

  @Override
  public KorisnikInfoDto dohvatiKorisnikInfo(String korisnickoIme) {
    Korisnik korisnik = manager.dohvati(korisnickoIme);
    if (korisnik == null) {
      throw new UpisiSeException(VrstaPoruke.KORISNIK_NE_POSTOJI_U_BAZI);
    }
    Studij studij = studijManager.dohvati(korisnik.getStudijId());
    return KorisnikMapper.toInfoDto(korisnik, studij);
  }

  @Override
  @Transactional
  public KorisnikDto azuriraj(AzurKorisnikaRequest request, String korisnickoIme) {
    Korisnik korisnik = manager.dohvati(korisnickoIme);
    if (korisnik == null) {
      throw new UpisiSeException(VrstaPoruke.KORISNIK_NE_POSTOJI_U_BAZI);
    }
    KorisnikMapper.pripremiZaAzuriranje(request, korisnik);
    manager.azuriraj(korisnik);
    return KorisnikMapper.toDto(
        korisnik,
        sifraOpisHelper.dohvatiVisokoUciliste(korisnik.getVisokoUcilisteId()),
        sifraOpisHelper.dohvatiStudij(korisnik.getStudijId()));
  }

  @Override
  public void izbrisi(String korisnickoIme) {
    manager.izbrisi(korisnickoIme);
  }
}
