package hr.lrukavina.upisisebackend.model.kolegij.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KolegijUpisniListDto {
  private String naziv;
  private Integer semestar;
  private String isvuSifra;
  private Integer ects;
  private boolean obavezan;
  private String cijena;
}
