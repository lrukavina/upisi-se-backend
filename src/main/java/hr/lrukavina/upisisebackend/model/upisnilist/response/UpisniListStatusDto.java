package hr.lrukavina.upisisebackend.model.upisnilist.response;

import hr.lrukavina.upisisebackend.model.upisnilist.UpisniStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpisniListStatusDto {
  private String ime;
  private String prezime;
  private String jmbag;
  private UpisniStatus status;
}
