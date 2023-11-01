package hr.lrukavina.upisisebackend.model.korisnik;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.exception.UpisiSeException;
import hr.lrukavina.upisisebackend.exception.VrstaPoruke;
import hr.lrukavina.upisisebackend.model.korisnik.request.AzurKorisnikaRequest;
import hr.lrukavina.upisisebackend.model.korisnik.response.KorisnikDto;
import hr.lrukavina.upisisebackend.model.visokouciliste.VisokoUciliste;
import hr.lrukavina.upisisebackend.model.visokouciliste.VisokoUcilisteManager;
import hr.lrukavina.upisisebackend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KorisnikServiceImpl implements KorisnikService {

  private final KorisnikManager manager;
  private final VisokoUcilisteManager visokoUcilisteManager;

  @Override
  public KorisnikDto dohvati(String korisnickoIme) {
    Korisnik korisnik = manager.dohvati(korisnickoIme);
    if (korisnik == null) {
      throw new UpisiSeException(VrstaPoruke.KORISNIK_NE_POSTOJI_U_BAZI);
    }
    return KorisnikMapper.toDto(korisnik, dohvatiVisokoUciliste(korisnik.getVisokoUcilisteId()));
  }

  private SifraOpis dohvatiVisokoUciliste(Integer id) {
    VisokoUciliste visokoUciliste = visokoUcilisteManager.dohvati(id);
    if (visokoUciliste == null) {
      return null;
    }
    return SifraOpis.builder()
        .sifra(Utils.sifrirajId(visokoUciliste.getId()))
        .opis(visokoUciliste.getNaziv())
        .build();
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
    return KorisnikMapper.toDto(korisnik, dohvatiVisokoUciliste(korisnik.getVisokoUcilisteId()));
  }

  @Override
  public void izbrisi(String korisnickoIme) {
    manager.izbrisi(korisnickoIme);
  }
}
