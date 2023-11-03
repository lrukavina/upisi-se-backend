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
  private String korisnickoIme;
  private String jmbag;
  private Integer semestar;
  private String adresa;
  private Rola rola;
  private SifraOpis visokoUciliste;
  private SifraOpis studij;
}
