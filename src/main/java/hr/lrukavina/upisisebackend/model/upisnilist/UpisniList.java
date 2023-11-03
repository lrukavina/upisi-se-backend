package hr.lrukavina.upisisebackend.model.upisnilist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpisniList {
  private Integer id;
  private Integer brojEctsa;
  private BigDecimal cijenaPoEctsu;
  private BigDecimal ukupnaCijena;
  private String upisniBroj;
  private UpisniStatus status;
  private Integer upisId;
  private Integer korisnikId;
}
