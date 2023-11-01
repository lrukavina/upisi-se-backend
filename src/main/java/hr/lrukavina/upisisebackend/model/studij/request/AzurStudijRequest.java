package hr.lrukavina.upisisebackend.model.studij.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class AzurStudijRequest {
  private String nazivStudija;
  private String nazivSmjera;
  private Integer ectsCijena;
  private String visokoUcilisteSifra;
}
