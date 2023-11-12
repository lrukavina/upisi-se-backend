package hr.lrukavina.upisisebackend.model.korisnik.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KorisnikInfoDto {
  private String ime;
  private String prezime;
  private String jmbag;
  private String nazivStudija;
  private String nazivSmjera;
  private Integer semestar;
}
