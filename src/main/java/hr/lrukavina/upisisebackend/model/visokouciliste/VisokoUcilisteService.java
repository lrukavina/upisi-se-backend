package hr.lrukavina.upisisebackend.model.visokouciliste;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.model.visokouciliste.request.AzurVisokoUcilisteRequest;
import hr.lrukavina.upisisebackend.model.visokouciliste.request.SpremiVisokoUcilisteRequest;
import hr.lrukavina.upisisebackend.model.visokouciliste.response.VisokoUcilisteDto;

import java.util.List;

public interface VisokoUcilisteService {
  List<SifraOpis> dohvatiZaPadajuciIzbornik();

  VisokoUcilisteDto dohvati(String sifra);

  List<VisokoUcilisteDto> dohvatiSve();

  VisokoUcilisteDto spremi(SpremiVisokoUcilisteRequest visokoUciliste);

  VisokoUcilisteDto azuriraj(AzurVisokoUcilisteRequest visokoUciliste, String sifra);

  void izbrisi(String sifra);
}
