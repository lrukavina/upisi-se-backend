package hr.lrukavina.upisisebackend.model.studij.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
public class AzurStudijRequest {
  private String nazivStudija;
  private String nazivSmjera;
  private BigDecimal ectsCijena;
  private String visokoUcilisteSifra;
}
