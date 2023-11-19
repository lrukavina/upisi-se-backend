package hr.lrukavina.upisisebackend.model.studij.response;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class StudijDto {
  private String sifra;
  private String nazivStudija;
  private String nazivSmjera;
  private BigDecimal ectsCijena;
  private String ectsCijenaFormatirana;
  private SifraOpis visokoUciliste;
}
