package hr.lrukavina.upisisebackend.model.ugovor;

import hr.lrukavina.upisisebackend.model.kolegij.Kolegij;
import hr.lrukavina.upisisebackend.model.kolegij.KolegijManager;
import hr.lrukavina.upisisebackend.model.kolegij.KolegijMapper;
import hr.lrukavina.upisisebackend.model.studij.Studij;
import hr.lrukavina.upisisebackend.model.ugovor.dto.UgovorKolegijDto;
import hr.lrukavina.upisisebackend.utils.Konstante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UgovorHelper {
  private final KolegijManager kolegijManager;

  public List<UgovorKolegijDto> dohvatiKolegijeZaUgovor(Integer upisniListId, Studij studij) {
    List<Kolegij> kolegiji = kolegijManager.dohvatiPoUpisniListId(upisniListId);
    return kolegiji.stream()
        .map(kolegij -> KolegijMapper.toUgovorKolegijDto(kolegij, studij))
        .collect(Collectors.toList());
  }

  public String generirajKlasu(Integer upisniListId) {
    String id = String.format("%03d", upisniListId);

    return Konstante.KLASA_PREFIX
        + Konstante.CRTA
        + id
        + Konstante.KOSA_CRTA
        + generirajGodinuMjesec(false)
        + Konstante.KOSA_CRTA
        + Konstante.KLASA_SUFIX;
  }

  private String generirajGodinuMjesec(boolean obrnuto) {
    LocalDate datum = LocalDate.now();
    String godina = String.valueOf(datum.getYear()).substring(2);
    String mjesec = String.format("%02d", datum.getMonthValue());

    if (obrnuto) {
      return godina + Konstante.CRTA + mjesec;
    }
    return mjesec + Konstante.CRTA + godina;
  }

  public String generirajUrbroj(Integer upisniListId) {
    String id = String.format("%03d", upisniListId);

    return Konstante.URBROJ_PREFIX
        + Konstante.CRTA
        + generirajGodinuMjesec(true)
        + Konstante.CRTA
        + id;
  }
}
