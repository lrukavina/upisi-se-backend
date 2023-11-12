package hr.lrukavina.upisisebackend.model.ugovor;

import hr.lrukavina.upisisebackend.model.ugovor.dto.UgovorKolegijDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ugovor {
  private String nazivStudija;
  private String nazivSmjera;
  private String klasa;
  private String urbroj;
  private String ime;
  private String prezime;
  private String jmbag;
  private Integer semestar;
  private List<UgovorKolegijDto> kolegiji;
  private String iban;
  private String pozivNaBroj;
}
