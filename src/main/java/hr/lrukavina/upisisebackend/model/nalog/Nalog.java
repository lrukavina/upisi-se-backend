package hr.lrukavina.upisisebackend.model.nalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  private String model;
  private String pozivNaBroj;
  private String sifraNamjene;
  private String opis;
}
