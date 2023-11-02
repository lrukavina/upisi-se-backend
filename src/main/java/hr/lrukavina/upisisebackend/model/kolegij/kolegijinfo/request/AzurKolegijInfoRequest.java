package hr.lrukavina.upisisebackend.model.kolegij.kolegijinfo.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AzurKolegijInfoRequest {
  private String sifra;
  private String informacije;
  private String kolegijSifra;
}
