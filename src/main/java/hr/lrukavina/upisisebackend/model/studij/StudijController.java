package hr.lrukavina.upisisebackend.model.studij;

import hr.lrukavina.upisisebackend.model.studij.request.AzurStudijRequest;
import hr.lrukavina.upisisebackend.model.studij.request.SpremiStudijRequest;
import hr.lrukavina.upisisebackend.model.studij.response.StudijDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/studij")
@RequiredArgsConstructor
public class StudijController {
  private final StudijService studijService;

  @GetMapping("/dohvati/{sifra}")
  public ResponseEntity<StudijDto> dohvati(@PathVariable final String sifra) {
    return ResponseEntity.ok(studijService.dohvati(sifra));
  }

  @PostMapping("/spremi")
  public ResponseEntity<StudijDto> spremi(@RequestBody SpremiStudijRequest request) {
    return ResponseEntity.ok(studijService.spremi(request));
  }

  @PutMapping("/azuriraj/{sifra}")
  public ResponseEntity<StudijDto> azuriraj(
      @RequestBody AzurStudijRequest request, @PathVariable final String sifra) {
    return ResponseEntity.ok(studijService.azuriraj(request, sifra));
  }

  @DeleteMapping("/izbrisi/{sifra}")
  public void izbrisi(@PathVariable final String sifra) {
    studijService.izbrisi(sifra);
  }
}
