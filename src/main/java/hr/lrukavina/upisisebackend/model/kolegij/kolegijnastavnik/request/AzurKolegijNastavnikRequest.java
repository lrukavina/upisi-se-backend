package hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AzurKolegijNastavnikRequest {
  private String sifra;
  private String ime;
  private String prezime;
  private String titula;
  private String kolegijSifra;
}
