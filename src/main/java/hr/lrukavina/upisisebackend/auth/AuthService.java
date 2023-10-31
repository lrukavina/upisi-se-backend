package hr.lrukavina.upisisebackend.auth;

import hr.lrukavina.upisisebackend.config.JwtService;
import hr.lrukavina.upisisebackend.model.korisnik.Korisnik;
import hr.lrukavina.upisisebackend.model.korisnik.KorisnikManager;
import hr.lrukavina.upisisebackend.utils.Konstante;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final KorisnikManager korisnikManager;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthResponse register(RegistracijaRequest request) {
    String korisnickoIme = generirajKorisnickoIme(request.getIme(), request.getPrezime());
    String email = korisnickoIme + Konstante.EMAIL_NASTAVAK;
    var korisnik =
        Korisnik.builder()
            .ime(request.getIme())
            .prezime(request.getPrezime())
            .jmbag(request.getJmbag())
            .adresa(request.getAdresa())
            .email(email)
            .korisnickoIme(korisnickoIme)
            .lozinka(passwordEncoder.encode(request.getLozinka()))
            .rola(request.getRola())
            .visokoUcilisteId(request.getVisokoUcilisteId())
            .build();
    korisnikManager.spremi(korisnik);
    var jwtToken = jwtService.generateToken(korisnik);
    return AuthResponse.builder().token(jwtToken).build();
  }

  private String generirajKorisnickoIme(String ime, String prezime) {
    String korisnickoIme = (ime.charAt(0) + prezime).toLowerCase(Locale.ROOT);
    return korisnickoIme.replaceAll("[čć]", "c").replaceAll("đ", "d").replaceAll("ž", "z");
  }

  public AuthResponse authenticate(AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getKorisnickoIme(), request.getLozinka()));
    var user = korisnikManager.dohvati(request.getKorisnickoIme());
    var jwtToken = jwtService.generateToken(user);
    return AuthResponse.builder().token(jwtToken).build();
  }
}
