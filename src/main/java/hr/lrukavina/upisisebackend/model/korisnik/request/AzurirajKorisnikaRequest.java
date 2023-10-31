package hr.lrukavina.upisisebackend.model.korisnik.request;

import hr.lrukavina.upisisebackend.auth.RegistracijaRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AzurirajKorisnikaRequest extends RegistracijaRequest {
  private String email;
  private String korisnickoIme;
}
