package hr.lrukavina.upisisebackend.model.korisnik;

import hr.lrukavina.upisisebackend.model.korisnik.request.AzurKorisnikaRequest;
import hr.lrukavina.upisisebackend.model.korisnik.response.KorisnikDto;
import hr.lrukavina.upisisebackend.model.korisnik.response.KorisnikInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/korisnik")
@RequiredArgsConstructor
public class KorisnikController {

  private final KorisnikService korisnikService;

  @GetMapping("/dohvati/studenti")
  public ResponseEntity<List<KorisnikDto>> dohvatiSveStudente() {
    return ResponseEntity.ok(korisnikService.dohvatiSveStudente());
  }

  @GetMapping("/dohvati/{korisnickoIme}")
  public ResponseEntity<KorisnikDto> dohvati(@PathVariable final String korisnickoIme) {
    return ResponseEntity.ok(korisnikService.dohvati(korisnickoIme));
  }

  @GetMapping("/dohvati/info/{korisnickoIme}")
  public ResponseEntity<KorisnikInfoDto> dohvatiInfo(@PathVariable final String korisnickoIme) {
    return ResponseEntity.ok(korisnikService.dohvatiKorisnikInfo(korisnickoIme));
  }

  @PutMapping("/azuriraj/{korisnickoIme}")
  public ResponseEntity<KorisnikDto> azuriraj(
      @RequestBody AzurKorisnikaRequest request, @PathVariable final String korisnickoIme) {
    return ResponseEntity.ok(korisnikService.azuriraj(request, korisnickoIme));
  }

  @DeleteMapping("/izbrisi/{korisnickoIme}")
  public void izbrisi(@PathVariable final String korisnickoIme) {
    korisnikService.izbrisi(korisnickoIme);
  }
}
