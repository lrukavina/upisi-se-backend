package hr.lrukavina.upisisebackend.model.korisnik;

import hr.lrukavina.upisisebackend.model.korisnik.request.AzurirajKorisnikaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/korisnik")
@RequiredArgsConstructor
public class KorisnikController {

  private final KorisnikService korisnikService;

  @PutMapping("/azuriraj/{korisnickoIme}")
  public ResponseEntity<Korisnik> azuriraj(
      @RequestBody AzurirajKorisnikaRequest request, @PathVariable final String korisnickoIme) {
    return ResponseEntity.ok(korisnikService.azuriraj(request, korisnickoIme));
  }

  @DeleteMapping("/izbrisi/{korisnickoIme}")
  public void izbrisi(@PathVariable final String korisnickoIme) {
    korisnikService.izbrisi(korisnickoIme);
  }
}
