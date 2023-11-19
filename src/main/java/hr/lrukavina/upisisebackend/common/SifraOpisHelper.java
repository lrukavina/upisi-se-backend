package hr.lrukavina.upisisebackend.common;

import hr.lrukavina.upisisebackend.model.kolegij.Kolegij;
import hr.lrukavina.upisisebackend.model.kolegij.KolegijManager;
import hr.lrukavina.upisisebackend.model.korisnik.Korisnik;
import hr.lrukavina.upisisebackend.model.korisnik.KorisnikManager;
import hr.lrukavina.upisisebackend.model.studij.Studij;
import hr.lrukavina.upisisebackend.model.studij.StudijManager;
import hr.lrukavina.upisisebackend.model.visokouciliste.VisokoUciliste;
import hr.lrukavina.upisisebackend.model.visokouciliste.VisokoUcilisteManager;
import hr.lrukavina.upisisebackend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SifraOpisHelper {
  private final VisokoUcilisteManager visokoUcilisteManager;
  private final StudijManager studijManager;
  private final KolegijManager kolegijManager;
  private final KorisnikManager korisnikManager;

  public SifraOpis dohvatiVisokoUciliste(Integer id) {
    VisokoUciliste visokoUciliste = visokoUcilisteManager.dohvati(id);
    if (visokoUciliste == null) {
      return null;
    }
    return SifraOpis.builder()
        .sifra(Utils.sifrirajId(visokoUciliste.getId()))
        .opis(visokoUciliste.getNaziv())
        .build();
  }

  public List<SifraOpis> dohvatiVisokaUcilista() {
    List<VisokoUciliste> visokaUcilista = visokoUcilisteManager.dohvatiSve();
    List<SifraOpis> visokaUcilistaSifOpis = new ArrayList<>();

    for (VisokoUciliste visokoUciliste : visokaUcilista) {
      SifraOpis sifraOpis =
          SifraOpis.builder()
              .sifra(Utils.sifrirajId(visokoUciliste.getId()))
              .opis(visokoUciliste.getNaziv())
              .build();
      visokaUcilistaSifOpis.add(sifraOpis);
    }
    return visokaUcilistaSifOpis;
  }

  public SifraOpis dohvatiStudij(Integer id) {
    Studij studij = studijManager.dohvati(id);
    if (studij == null) {
      return null;
    }
    return SifraOpis.builder()
        .sifra(Utils.sifrirajId(studij.getId()))
        .opis(studij.getNazivStudija() + "(" + studij.getNazivSmjera() + ")")
        .build();
  }

  public SifraOpis dohvatiStudij(Studij studij) {
    if (studij == null) {
      return null;
    }
    return SifraOpis.builder()
        .sifra(Utils.sifrirajId(studij.getId()))
        .opis(studij.getNazivStudija() + " (" + studij.getNazivSmjera() + ")")
        .build();
  }

  public List<SifraOpis> dohvatiStudije(String sifra) {
    List<Studij> visokaUcilista =
        studijManager.dohvatiPoVisokoUcilisteId(Utils.desifrirajId(sifra));
    List<SifraOpis> studijiSifOpis = new ArrayList<>();

    for (Studij studij : visokaUcilista) {
      SifraOpis sifraOpis =
          SifraOpis.builder()
              .sifra(Utils.sifrirajId(studij.getId()))
              .opis(studij.getNazivStudija() + " (" + studij.getNazivSmjera() + ")")
              .build();
      studijiSifOpis.add(sifraOpis);
    }
    return studijiSifOpis;
  }

  public SifraOpis dohvatiKolegij(Kolegij kolegij) {
    if (kolegij == null) {
      return null;
    }
    return SifraOpis.builder()
        .sifra(Utils.sifrirajId(kolegij.getId()))
        .opis(kolegij.getNaziv())
        .build();
  }

  public SifraOpisKolegij dohvatiKolegijUpis(Kolegij kolegij) {
    if (kolegij == null) {
      return null;
    }
    return SifraOpisKolegij.builder()
        .sifra(Utils.sifrirajId(kolegij.getId()))
        .opis(kolegij.getNaziv())
        .semestar(kolegij.getSemestar())
        .ects(kolegij.getEcts())
        .build();
  }

  public SifraOpis dohvatiKorisnika(Integer id) {
    Korisnik korisnik = korisnikManager.dohvati(id);
    if (korisnik == null) {
      return null;
    }
    return SifraOpis.builder()
        .sifra(Utils.sifrirajId(korisnik.getId()))
        .opis(korisnik.getIme() + " " + korisnik.getPrezime())
        .build();
  }
}
