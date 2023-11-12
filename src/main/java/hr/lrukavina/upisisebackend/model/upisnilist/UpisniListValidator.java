package hr.lrukavina.upisisebackend.model.upisnilist;

import hr.lrukavina.upisisebackend.exception.UpisiSeException;
import hr.lrukavina.upisisebackend.exception.VrstaPoruke;
import hr.lrukavina.upisisebackend.model.kolegij.Kolegij;
import hr.lrukavina.upisisebackend.model.kolegij.KolegijManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpisniListValidator {
  private final KolegijManager kolegijManager;

  public void validirajOdabraneKolegije(List<Kolegij> odabraniKolegiji, Integer upisId) {
    List<Kolegij> kolegiji = kolegijManager.dohvatiPoUpisId(upisId);
    boolean nedozvoljeniKolegij =
        odabraniKolegiji.stream().anyMatch(odabraniKolegij -> !kolegiji.contains(odabraniKolegij));

    if (nedozvoljeniKolegij) {
      throw new UpisiSeException(VrstaPoruke.UPIS_NEDOZVOLJENI_KOLEGIJ);
    }
  }
}
