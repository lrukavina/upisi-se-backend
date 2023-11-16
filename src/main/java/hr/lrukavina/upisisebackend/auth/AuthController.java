package hr.lrukavina.upisisebackend.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/registracija")
  public ResponseEntity<AuthResponse> register(@RequestBody RegistracijaRequest request) {
    return ResponseEntity.ok(authService.register(request));
  }

  @PostMapping("/autentikacija")
  public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
    return ResponseEntity.ok(authService.authenticate(request));
  }
}
