package hr.lrukavina.upisisebackend.model.korisnik;

import hr.lrukavina.upisisebackend.model.korisnik.request.AzurKorisnikaRequest;
import hr.lrukavina.upisisebackend.model.korisnik.response.KorisnikDto;
import hr.lrukavina.upisisebackend.model.korisnik.response.KorisnikInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/korisnik")
@RequiredArgsConstructor
public class KorisnikController {

  private final KorisnikService korisnikService;

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
