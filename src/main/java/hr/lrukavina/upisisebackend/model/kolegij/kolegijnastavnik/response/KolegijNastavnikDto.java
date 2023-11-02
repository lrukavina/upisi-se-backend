package hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik.response;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KolegijNastavnikDto {
  private String sifra;
  private String ime;
  private String prezime;
  private String titula;
  private SifraOpis kolegij;
}
