package hr.lrukavina.upisisebackend.model.upisnilist;

import hr.lrukavina.upisisebackend.model.upisnilist.request.AzurUpisniListRequest;
import hr.lrukavina.upisisebackend.model.upisnilist.response.UpisniListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PutMapping("/azuriraj/{korisnickoIme}")
  public ResponseEntity<UpisniListDto> azuriraj(
      @RequestBody AzurUpisniListRequest request, @PathVariable final String korisnickoIme) {
    return ResponseEntity.ok(upisniListService.azuriraj(request, korisnickoIme));
  }

  @DeleteMapping("/izbrisi/{sifra}")
  public void izbrisi(@PathVariable final String sifra) {
    upisniListService.izbrisi(sifra);
  }
}
