package hr.lrukavina.upisisebackend.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SifraOpisKolegij {
  private String sifra;
  private String opis;
  private Integer semestar;
  private Integer ects;
}
