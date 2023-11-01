package hr.lrukavina.upisisebackend.model.visokouciliste.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class AzurVisokoUcilisteRequest {
  private String sifra;
  private String naziv;
  private String adresa;
  private String postanskiBroj;
  private String mjesto;
  private String iban;
  private String oib;
}
