package hr.lrukavina.upisisebackend.model.kolegij;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KolegijNastavnik {
  private Integer id;
  private String ime;
  private String prezime;
  private String titula;
  private Integer kolegijId;
}
