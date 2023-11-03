package hr.lrukavina.upisisebackend.model.upis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Upis {
  private Integer id;
  private Integer semestar;
  private Integer minBrojEctsa;
  private Integer maxBrojEctsa;
  private LocalDateTime tstampOd;
  private LocalDateTime tstampDo;
  private Integer studijId;
}
