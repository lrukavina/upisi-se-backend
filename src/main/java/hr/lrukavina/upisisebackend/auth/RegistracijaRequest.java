package hr.lrukavina.upisisebackend.auth;

import hr.lrukavina.upisisebackend.model.korisnik.Rola;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class RegistracijaRequest {
  private String ime;
  private String prezime;
  private String jmbag;
  private String adresa;
  private String email;
  private String korisnickoIme;
  private String lozinka;
  private Rola rola;
  private String visokoUcilisteSifra;
}
