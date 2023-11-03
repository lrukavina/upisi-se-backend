package hr.lrukavina.upisisebackend.model.studij;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Studij {
  private Integer id;
  private String nazivStudija;
  private String nazivSmjera;
  private BigDecimal ectsCijena;
  private Integer visokoUcilisteId;
}
