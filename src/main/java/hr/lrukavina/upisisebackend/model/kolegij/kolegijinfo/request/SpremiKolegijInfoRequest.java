package hr.lrukavina.upisisebackend.model.kolegij.kolegijinfo.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpremiKolegijInfoRequest {
  private String informacije;
  private String kolegijSifra;
}
