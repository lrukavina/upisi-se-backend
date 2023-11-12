package hr.lrukavina.upisisebackend.model.upis;

import hr.lrukavina.upisisebackend.model.upis.request.AzurUpisRequest;
import hr.lrukavina.upisisebackend.model.upis.request.SpremiUpisRequest;
import hr.lrukavina.upisisebackend.model.upis.response.UpisDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/upis")
@RequiredArgsConstructor
public class UpisController {
  private final UpisService upisService;

  @GetMapping("/dohvati/{sifra}")
  public ResponseEntity<UpisDto> dohvati(@PathVariable final String sifra) {
    return ResponseEntity.ok(upisService.dohvati(sifra));
  }

  @GetMapping("/dohvati/aktivne/korisnik/{korisnickoIme}")
  public ResponseEntity<UpisDto> dohvatiAktivneZaKorisnika(
      @PathVariable final String korisnickoIme) {
    return ResponseEntity.ok(upisService.dohvatiAktivneZaKorisnika(korisnickoIme));
  }

  @PostMapping("/spremi")
  public ResponseEntity<UpisDto> spremi(@RequestBody SpremiUpisRequest request) {
    return ResponseEntity.ok(upisService.spremi(request));
  }

  @PutMapping("/azuriraj/{sifra}")
  public ResponseEntity<UpisDto> azuriraj(
      @RequestBody AzurUpisRequest request, @PathVariable final String sifra) {
    return ResponseEntity.ok(upisService.azuriraj(request, sifra));
  }

  @DeleteMapping("/izbrisi/{sifra}")
  public void izbrisi(@PathVariable final String sifra) {
    upisService.izbrisi(sifra);
  }
}
