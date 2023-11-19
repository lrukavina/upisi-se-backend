package hr.lrukavina.upisisebackend.model.upisnilist;

import hr.lrukavina.upisisebackend.model.upisnilist.request.AzurUpisniListRequest;
import hr.lrukavina.upisisebackend.model.upisnilist.request.PotvrdiUpisniListRequest;
import hr.lrukavina.upisisebackend.model.upisnilist.response.UpisniListDto;
import hr.lrukavina.upisisebackend.model.upisnilist.response.UpisniListStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/upisni-list")
@RequiredArgsConstructor
public class UpisniListController {

  private final UpisniListService upisniListService;

  @GetMapping("/dohvati/{sifra}")
  public ResponseEntity<UpisniListDto> dohvati(@PathVariable final String sifra) {
    return ResponseEntity.ok(upisniListService.dohvati(sifra));
  }

  @GetMapping("/dohvati/korisnik/{korisnickoIme}")
  public ResponseEntity<UpisniListDto> dohvatiPoKorisniku(
      @PathVariable final String korisnickoIme) {
    return ResponseEntity.ok(upisniListService.dohvatiPoKorisniku(korisnickoIme));
  }

  @GetMapping("/dohvati/status/upis/{sifra}")
  public ResponseEntity<List<UpisniListStatusDto>> dohvatiUpisniListStatuse(
      @PathVariable final String sifra) {
    return ResponseEntity.ok(upisniListService.dohvatiUpisniListStatuse(sifra));
  }

  @PutMapping("/azuriraj/{korisnickoIme}")
  public ResponseEntity<UpisniListDto> azuriraj(
      @RequestBody AzurUpisniListRequest request, @PathVariable final String korisnickoIme) {
    return ResponseEntity.ok(upisniListService.azuriraj(request, korisnickoIme));
  }

  @PutMapping("/potvrdi/{korisnickoIme}")
  public ResponseEntity<UpisniListDto> potvrdi(
      @RequestBody PotvrdiUpisniListRequest request, @PathVariable final String korisnickoIme) {
    return ResponseEntity.ok(upisniListService.potvrdi(request));
  }

  @DeleteMapping("/izbrisi/{sifra}")
  public void izbrisi(@PathVariable final String sifra) {
    upisniListService.izbrisi(sifra);
  }

  @DeleteMapping("/izbrisi/korisnik/{korisnickoIme}")
  public void izbrisiPoKorisniku(@PathVariable final String korisnickoIme) {
    upisniListService.izbrisiPoKorisniku(korisnickoIme);
  }
}
