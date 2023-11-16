package hr.lrukavina.upisisebackend.model.upis;

import hr.lrukavina.upisisebackend.model.upis.request.AzurUpisRequest;
import hr.lrukavina.upisisebackend.model.upis.request.SpremiUpisRequest;
import hr.lrukavina.upisisebackend.model.upis.response.UpisDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

  @GetMapping(
      path = "/dohvati/pregled/korisnik/{korisnickoIme}",
      produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<byte[]> dohvatiPregledUpisa(@PathVariable final String korisnickoIme)
      throws IOException {
    ByteArrayOutputStream pdf = upisService.dohvatiPregledUpisa(korisnickoIme);
    return ResponseEntity.ok(pdf.toByteArray());
  }

  @DeleteMapping("/izbrisi/{sifra}")
  public void izbrisi(@PathVariable final String sifra) {
    upisService.izbrisi(sifra);
  }
}
