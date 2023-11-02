package hr.lrukavina.upisisebackend.model.upis.upisInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpisInfo {
  private Integer id;
  private String pozivNaBroj;
  private Integer brojEctsa;
  private String upisniBroj;
  private String opis;
  private UpisniStatus status;
  private Integer upisId;
  private Integer korisnikId;
}
