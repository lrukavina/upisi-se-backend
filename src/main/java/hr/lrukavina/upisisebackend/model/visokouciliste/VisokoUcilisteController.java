package hr.lrukavina.upisisebackend.model.visokouciliste;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.model.visokouciliste.request.AzurVisokoUcilisteRequest;
import hr.lrukavina.upisisebackend.model.visokouciliste.request.SpremiVisokoUcilisteRequest;
import hr.lrukavina.upisisebackend.model.visokouciliste.response.VisokoUcilisteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/visoko-uciliste")
@RequiredArgsConstructor
public class VisokoUcilisteController {

  private final VisokoUcilisteService visokoUcilisteService;

  @GetMapping("/dohvati/padajuci-izbornik")
  public ResponseEntity<List<SifraOpis>> dohvatiZaPadajuciIzbornik() {
    return ResponseEntity.ok(visokoUcilisteService.dohvatiZaPadajuciIzbornik());
  }

  @GetMapping("/dohvati/{sifra}")
  public ResponseEntity<VisokoUcilisteDto> dohvati(@PathVariable final String sifra) {
    return ResponseEntity.ok(visokoUcilisteService.dohvati(sifra));
  }

  @GetMapping("/dohvati/sve")
  public ResponseEntity<List<VisokoUcilisteDto>> dohvatiSve() {
    return ResponseEntity.ok(visokoUcilisteService.dohvatiSve());
  }

  @PostMapping("/spremi")
  public ResponseEntity<VisokoUcilisteDto> spremi(
      @RequestBody SpremiVisokoUcilisteRequest request) {
    return ResponseEntity.ok(visokoUcilisteService.spremi(request));
  }

  @PutMapping("/azuriraj/{sifra}")
  public ResponseEntity<VisokoUcilisteDto> azuriraj(
      @RequestBody AzurVisokoUcilisteRequest request, @PathVariable final String sifra) {
    return ResponseEntity.ok(visokoUcilisteService.azuriraj(request, sifra));
  }

  @DeleteMapping("/izbrisi/{sifra}")
  public void izbrisi(@PathVariable final String sifra) {
    visokoUcilisteService.izbrisi(sifra);
  }
}
