package hr.lrukavina.upisisebackend.model.kolegij.kolegijinfo.response;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KolegijInfoDto {
  private String sifra;
  private String informacije;
  private SifraOpis kolegij;
}
