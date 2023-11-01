package hr.lrukavina.upisisebackend.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SifraOpis {
  private String sifra;
  private String opis;
}
