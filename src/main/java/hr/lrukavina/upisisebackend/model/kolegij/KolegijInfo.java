package hr.lrukavina.upisisebackend.model.kolegij;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KolegijInfo {
  private Integer id;
  private String informacije;
  private Integer kolegijId;
}
