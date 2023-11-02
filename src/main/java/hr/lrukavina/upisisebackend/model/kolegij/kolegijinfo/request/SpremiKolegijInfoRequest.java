package hr.lrukavina.upisisebackend.model.kolegij.kolegijinfo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpremiKolegijInfoRequest {
  private String informacije;
}
