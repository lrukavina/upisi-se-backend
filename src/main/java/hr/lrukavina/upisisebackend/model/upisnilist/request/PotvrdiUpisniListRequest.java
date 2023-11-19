package hr.lrukavina.upisisebackend.model.upisnilist.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PotvrdiUpisniListRequest {
  private String korisnickoIme;
}
