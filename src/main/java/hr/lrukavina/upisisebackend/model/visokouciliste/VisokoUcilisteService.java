package hr.lrukavina.upisisebackend.model.visokouciliste;

import hr.lrukavina.upisisebackend.model.visokouciliste.request.AzurVisokoUcilisteRequest;
import hr.lrukavina.upisisebackend.model.visokouciliste.request.SpremiVisokoUcilisteRequest;
import hr.lrukavina.upisisebackend.model.visokouciliste.response.VisokoUcilisteDto;

public interface VisokoUcilisteService {
  VisokoUcilisteDto dohvati(String sifra);

  VisokoUcilisteDto spremi(SpremiVisokoUcilisteRequest visokoUciliste);

  VisokoUcilisteDto azuriraj(AzurVisokoUcilisteRequest visokoUciliste, String sifra);

  void izbrisi(String sifra);
}
