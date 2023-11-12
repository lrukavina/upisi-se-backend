package hr.lrukavina.upisisebackend.model.ugovor;

import hr.lrukavina.upisisebackend.model.korisnik.Korisnik;
import hr.lrukavina.upisisebackend.model.korisnik.KorisnikManager;
import hr.lrukavina.upisisebackend.model.studij.Studij;
import hr.lrukavina.upisisebackend.model.studij.StudijManager;
import hr.lrukavina.upisisebackend.model.ugovor.dto.UgovorKolegijDto;
import hr.lrukavina.upisisebackend.model.upisnilist.UpisniList;
import hr.lrukavina.upisisebackend.model.visokouciliste.VisokoUciliste;
import hr.lrukavina.upisisebackend.model.visokouciliste.VisokoUcilisteManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UgovorServiceImpl implements UgovorService {
  private final StudijManager studijManager;
  private final KorisnikManager korisnikManager;
  private final VisokoUcilisteManager visokoUcilisteManager;
  private final UgovorHelper helper;

  @Override
  public Ugovor generirajZaUpisniList(UpisniList upisniList) {
    Studij studij = studijManager.dohvatiPoUpisniListId(upisniList.getId());
    Korisnik korisnik = korisnikManager.dohvatiPoUpisniListId(upisniList.getId());
    VisokoUciliste visokoUciliste = visokoUcilisteManager.dohvatiPoUpisniListId(upisniList.getId());
    List<UgovorKolegijDto> kolegiji = helper.dohvatiKolegijeZaUgovor(upisniList.getId(), studij);
    String klasa = helper.generirajKlasu(upisniList.getId());
    String urbroj = helper.generirajUrbroj(upisniList.getId());

    return UgovorMapper.toUgovor(studij, korisnik, visokoUciliste, kolegiji, klasa, urbroj);
  }
}
