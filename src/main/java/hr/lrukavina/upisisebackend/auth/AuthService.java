package hr.lrukavina.upisisebackend.auth;

import hr.lrukavina.upisisebackend.config.JwtService;
import hr.lrukavina.upisisebackend.model.korisnik.Korisnik;
import hr.lrukavina.upisisebackend.model.korisnik.KorisnikManager;
import hr.lrukavina.upisisebackend.model.korisnik.KorisnikService;
import hr.lrukavina.upisisebackend.utils.Konstante;
import hr.lrukavina.upisisebackend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final KorisnikManager korisnikManager;
  private final KorisnikService korisnikService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Transactional
  public AuthResponse register(RegistracijaRequest request) {
    String korisnickoIme =
        korisnikService.generirajKorisnickoIme(request.getIme(), request.getPrezime());
    String email = korisnickoIme + Konstante.EMAIL_NASTAVAK;
    var korisnik =
        Korisnik.builder()
            .ime(request.getIme())
            .prezime(request.getPrezime())
            .jmbag(request.getJmbag())
            .semestar(request.getSemestar())
            .adresa(request.getAdresa())
            .email(email)
            .korisnickoIme(korisnickoIme)
            .lozinka(passwordEncoder.encode(request.getLozinka()))
            .rola(request.getRola())
            .visokoUcilisteId(Utils.desifrirajId(request.getVisokoUcilisteSifra()))
            .studijId(Utils.desifrirajId(request.getStudijSifra()))
            .build();
    korisnikManager.spremi(korisnik);
    var jwtToken = jwtService.generateToken(korisnik);
    return AuthResponse.builder()
        .token(jwtToken)
        .korisnickoIme(korisnik.getKorisnickoIme())
        .rola(korisnik.getRola())
        .build();
  }

  public AuthResponse authenticate(AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getKorisnickoIme(), request.getLozinka()));
    var user = korisnikManager.dohvati(request.getKorisnickoIme());
    var jwtToken = jwtService.generateToken(user);
    return AuthResponse.builder()
        .token(jwtToken)
        .korisnickoIme(user.getKorisnickoIme())
        .rola(user.getRola())
        .build();
  }
}
