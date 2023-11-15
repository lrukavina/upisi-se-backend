package hr.lrukavina.upisisebackend.model.nalog;

import hr.lrukavina.upisisebackend.utils.Konstante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Nalog {
  private String zaglavlje;
  private String valuta;
  private String iznos;
  private String platitelj;
  private Adresa adresaPlatitelja;
  private String primatelj;
  private Adresa adresaPrimatelja;
  private String ibanPrimatelja;
  private String modelDrzava;
  private String modelBroj;
  private String pozivNaBroj;
  private String sifraNamjene;
  private String opis;

  public static String dodajRazmake(String podatak) {
    StringBuilder podatakRazmaci = new StringBuilder();
    for (int i = 0; i < podatak.length(); i++) {
      podatakRazmaci.append(podatak.charAt(i)).append(Konstante.RAZMAK).append(Konstante.RAZMAK);
    }
    return podatakRazmaci.toString();
  }

  public static List<String> prelomi(int brZnakova, String podatak) {
    List<String> podaci = new ArrayList<>();
    int index = 0;
    while (index < podatak.length()) {
      podaci.add(podatak.substring(index, Math.min(index + brZnakova,podatak.length())));
      index += brZnakova;
    }
    return podaci;
  }

  public String getModelBarkod() {
    return this.modelDrzava + this.modelBroj;
  }
}
