package hr.lrukavina.upisisebackend.model.nalog;

import hr.lrukavina.upisisebackend.utils.Konstante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Adresa {
  private String ulica;
  private String kucniBroj;
  private String postanskiBroj;
  private String mjesto;

  public String formatirajZaNalog() {
    return this.ulica
        + Konstante.RAZMAK
        + this.kucniBroj
        + Konstante.ZAREZ
        + Konstante.RAZMAK
        + this.mjesto;
  }

  public String dohvatiUlicuKucniBr() {
    return this.ulica + Konstante.RAZMAK + this.kucniBroj;
  }

  public String dohvatiPostanskiBrMjesto() {
    return this.postanskiBroj + Konstante.RAZMAK + this.mjesto;
  }
}
