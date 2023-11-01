package hr.lrukavina.upisisebackend.model.visokouciliste;

import hr.lrukavina.upisisebackend.model.visokouciliste.request.AzurVisokoUcilisteRequest;
import hr.lrukavina.upisisebackend.model.visokouciliste.response.VisokoUcilisteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visoko-uciliste")
@RequiredArgsConstructor
public class VisokoUcilisteController {

  private final VisokoUcilisteService visokoUcilisteService;

  @GetMapping("/dohvati/{sifra}")
  public ResponseEntity<VisokoUcilisteDto> dohvati(@PathVariable final String sifra) {
    return ResponseEntity.ok(visokoUcilisteService.dohvati(sifra));
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
