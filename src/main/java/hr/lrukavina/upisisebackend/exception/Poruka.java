package hr.lrukavina.upisisebackend.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Poruka {
  private String sifra;
  private String poruka;
  private String referenca;

  public static Poruka buildPoruka(VrstaPoruke vrstaPoruke, String referenca) {
    return Poruka.builder()
        .sifra(vrstaPoruke.getSifra())
        .poruka(vrstaPoruke.getPoruka())
        .referenca(referenca)
        .build();
  }

  public static Poruka buildPoruka(VrstaPoruke vrstaPoruke) {
    return buildPoruka(vrstaPoruke, null);
  }
}
