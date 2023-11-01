package hr.lrukavina.upisisebackend.common;

import hr.lrukavina.upisisebackend.model.visokouciliste.VisokoUciliste;
import hr.lrukavina.upisisebackend.model.visokouciliste.VisokoUcilisteManager;
import hr.lrukavina.upisisebackend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SifraOpisHelper {
  private final VisokoUcilisteManager visokoUcilisteManager;

  public SifraOpis dohvatiVisokoUciliste(Integer id) {
    VisokoUciliste visokoUciliste = visokoUcilisteManager.dohvati(id);
    if (visokoUciliste == null) {
      return null;
    }
    return SifraOpis.builder()
        .sifra(Utils.sifrirajId(visokoUciliste.getId()))
        .opis(visokoUciliste.getNaziv())
        .build();
  }
}
