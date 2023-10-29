package hr.lrukavina.upisisebackend.auth;

import hr.lrukavina.upisisebackend.model.korisnik.Rola;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegistracijaRequest {
  private String ime;
  private String prezime;
  private String lozinka;
  private Rola rola;
}
