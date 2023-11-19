package hr.lrukavina.upisisebackend.model.kolegij;

import hr.lrukavina.upisisebackend.model.kolegij.request.AzurKolegijRequest;
import hr.lrukavina.upisisebackend.model.kolegij.request.SpremiKolegijRequest;
import hr.lrukavina.upisisebackend.model.kolegij.response.KolegijDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/kolegij")
@RequiredArgsConstructor
public class KolegijController {
  private final KolegijService kolegijService;

  @GetMapping("/dohvati/{sifra}")
  public ResponseEntity<KolegijDto> dohvati(@PathVariable final String sifra) {
    return ResponseEntity.ok(kolegijService.dohvati(sifra));
  }

  @GetMapping("/dohvati/sve")
  public ResponseEntity<List<KolegijDto>> dohvatiSve() {
    return ResponseEntity.ok(kolegijService.dohvatiSve());
  }

  @PostMapping("/spremi")
  public ResponseEntity<KolegijDto> spremi(@RequestBody SpremiKolegijRequest request) {
    return ResponseEntity.ok(kolegijService.spremi(request));
  }

  @PutMapping("/azuriraj/{sifra}")
  public ResponseEntity<KolegijDto> azuriraj(
      @RequestBody AzurKolegijRequest request, @PathVariable final String sifra) {
    return ResponseEntity.ok(kolegijService.azuriraj(request, sifra));
  }

  @DeleteMapping("/izbrisi/{sifra}")
  public void izbrisi(@PathVariable final String sifra) {
    kolegijService.izbrisi(sifra);
  }
}
