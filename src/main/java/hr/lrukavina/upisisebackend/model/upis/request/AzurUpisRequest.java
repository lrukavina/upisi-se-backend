package hr.lrukavina.upisisebackend.model.upis.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
public class AzurUpisRequest {
  private String studijSifra;
  private Integer semestar;
  private Integer minBrojEctsa;
  private Integer maxBrojEctsa;
  private LocalDateTime tstampOd;
  private LocalDateTime tstampDo;
  private List<String> obavezniKolegijiSifre;
  private List<String> izborniKolegijiSifre;
}
