package hr.lrukavina.upisisebackend.model.nalog;

import hr.lrukavina.upisisebackend.model.korisnik.Korisnik;
import hr.lrukavina.upisisebackend.model.korisnik.KorisnikManager;
import hr.lrukavina.upisisebackend.model.upisnilist.UpisniList;
import hr.lrukavina.upisisebackend.model.visokouciliste.VisokoUciliste;
import hr.lrukavina.upisisebackend.model.visokouciliste.VisokoUcilisteManager;
import hr.lrukavina.upisisebackend.utils.Konstante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class NalogServiceImpl implements NalogService {
  private final KorisnikManager korisnikManager;
  private final VisokoUcilisteManager visokoUcilisteManager;

  @Override
  public Nalog generirajZaUpisniList(UpisniList upisniList) {
    Korisnik korisnik = korisnikManager.dohvatiPoUpisniListId(upisniList.getId());
    VisokoUciliste visokoUciliste = visokoUcilisteManager.dohvatiPoUpisniListId(upisniList.getId());

    return Nalog.builder()
        .zaglavlje(Konstante.NALOG_ZAGLAVLJE)
        .valuta(Konstante.NALOG_VALUTA)
        .iznos(String.valueOf(upisniList.getUkupnaCijena()))
        .platitelj(korisnik.getImePrezime())
        .adresaPlatitelja(blankAdresa())
        .primatelj(visokoUciliste.getNaziv())
        .adresaPrimatelja(dohvatiAdresu(visokoUciliste))
        .ibanPrimatelja(visokoUciliste.getIban())
        .model(Konstante.NALOG_MODEL)
        .pozivNaBroj(generirajPozivNaBroj(korisnik.getJmbag()))
        .sifraNamjene(Konstante.NALOG_SIF_NAMJENE)
        .opis(generirajOpis(upisniList.getUpisniBroj(), korisnik.getSemestar()))
        .build();
  }

  private Adresa blankAdresa() {
    return Adresa.builder()
        .ulica(Konstante.PRAZAN_ZNAK)
        .kucniBroj(Konstante.PRAZAN_ZNAK)
        .postanskiBroj(Konstante.PRAZAN_ZNAK)
        .mjesto(Konstante.PRAZAN_ZNAK)
        .build();
  }

  private Adresa dohvatiAdresu(VisokoUciliste visokoUciliste) {
    String ulica = visokoUciliste.getAdresa().replaceAll("[^a-zA-Z].*", "");
    String kucniBroj = visokoUciliste.getAdresa().replaceAll("[^0-9]", "");
    return Adresa.builder()
        .ulica(ulica)
        .kucniBroj(kucniBroj)
        .postanskiBroj(visokoUciliste.getPostanskiBroj())
        .mjesto(visokoUciliste.getMjesto())
        .build();
  }

  private String generirajPozivNaBroj(String jmbag) {
    return Konstante.POZIV_NA_BROJ_PREFIX + jmbag + Konstante.POZIV_NA_BROJ_SUFIX;
  }

  private String generirajOpis(String upisniBroj, Integer semestar) {
    return "Školarina"
        + Konstante.RAZMAK
        + LocalDate.now().getYear()
        + Konstante.KOSA_CRTA
        + LocalDate.now().plusYears(1).getYear()
        + Konstante.RAZMAK
        + dohvatiOpisZaSemestar(semestar)
        + Konstante.RAZMAK
        + "SIF: "
        + upisniBroj;
  }

  private String dohvatiOpisZaSemestar(Integer semestar) {
    if (semestar % 2 == 0) {
      return Konstante.NALOG_LJETNI_SEM;
    }
    return Konstante.NALOG_ZIMSKI_SEM;
  }
}
