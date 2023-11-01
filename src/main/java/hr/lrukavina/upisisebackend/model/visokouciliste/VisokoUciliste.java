package hr.lrukavina.upisisebackend.model.visokouciliste;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisokoUciliste {
  private Integer id;
  private String naziv;
  private String adresa;
  private String postanskiBroj;
  private String mjesto;
  private String iban;
  private String oib;
}
