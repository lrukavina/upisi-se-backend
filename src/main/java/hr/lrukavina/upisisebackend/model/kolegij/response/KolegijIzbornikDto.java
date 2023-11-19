package hr.lrukavina.upisisebackend.model.kolegij.response;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class KolegijIzbornikDto {
  private List<SifraOpis> obavezniKolegiji;
  private List<SifraOpis> izborniKolegiji;
}
