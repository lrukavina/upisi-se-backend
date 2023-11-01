package hr.lrukavina.upisisebackend.model.visokouciliste.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VisokoUcilisteDto {
  private String sifra;
  private String naziv;
  private String adresa;
  private String postanskiBroj;
  private String mjesto;
  private String iban;
  private String oib;
}
