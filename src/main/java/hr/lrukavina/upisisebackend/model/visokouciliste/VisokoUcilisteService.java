package hr.lrukavina.upisisebackend.model.visokouciliste;

import hr.lrukavina.upisisebackend.model.visokouciliste.request.AzurVisokoUcilisteRequest;
import hr.lrukavina.upisisebackend.model.visokouciliste.request.SpremiVisokoUcilisteRequest;
import hr.lrukavina.upisisebackend.model.visokouciliste.response.VisokoUcilisteDto;

public interface VisokoUcilisteService {
  VisokoUcilisteDto dohvati(Integer visokoUcilisteId);

  void spremi(SpremiVisokoUcilisteRequest visokoUciliste);

  void azuriraj(AzurVisokoUcilisteRequest visokoUciliste);

  void izbrisi(Integer visokoUcilisteId);
}
