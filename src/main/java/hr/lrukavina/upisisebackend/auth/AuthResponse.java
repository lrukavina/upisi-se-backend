package hr.lrukavina.upisisebackend.auth;

import hr.lrukavina.upisisebackend.model.korisnik.Rola;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
  private String token;
  private String korisnickoIme;
  private Rola rola;
}
