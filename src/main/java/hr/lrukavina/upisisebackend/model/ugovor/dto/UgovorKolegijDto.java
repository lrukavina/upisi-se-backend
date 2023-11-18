package hr.lrukavina.upisisebackend.model.ugovor.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class UgovorKolegijDto {
  private String isvuSifra;
  private String naziv;
  private Integer ects;
  private BigDecimal ectsCijena;
  private boolean obavezan;
}
