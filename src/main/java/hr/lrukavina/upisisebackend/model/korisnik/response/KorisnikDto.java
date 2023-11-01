package hr.lrukavina.upisisebackend.model.korisnik.response;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.model.korisnik.Rola;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KorisnikDto {
  private String ime;
  private String prezime;
  private String jmbag;
  private String adresa;
  private String lozinka;
  private Rola rola;
  private SifraOpis visokoUciliste;
}
