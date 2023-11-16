package hr.lrukavina.upisisebackend.model.nalog;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PutanjaDatoteke {
  private String barkodPutanja;
  private String hub3Putanja;
}
