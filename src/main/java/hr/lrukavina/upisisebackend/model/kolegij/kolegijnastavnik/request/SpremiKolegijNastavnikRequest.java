package hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpremiKolegijNastavnikRequest {
  private String ime;
  private String prezime;
  private String titula;
}
