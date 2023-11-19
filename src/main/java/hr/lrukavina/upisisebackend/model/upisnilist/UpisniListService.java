package hr.lrukavina.upisisebackend.model.upisnilist;

import hr.lrukavina.upisisebackend.model.upis.Upis;
import hr.lrukavina.upisisebackend.model.upisnilist.request.AzurUpisniListRequest;
import hr.lrukavina.upisisebackend.model.upisnilist.request.PotvrdiUpisniListRequest;
import hr.lrukavina.upisisebackend.model.upisnilist.response.UpisniListDto;
import hr.lrukavina.upisisebackend.model.upisnilist.response.UpisniListStatusDto;

import java.util.List;

public interface UpisniListService {
  UpisniListDto dohvati(String sifra);

  UpisniListDto dohvatiPoKorisniku(String korisnikSifra);

  UpisniListDto azuriraj(AzurUpisniListRequest request, String sifra);

  UpisniListDto potvrdi(PotvrdiUpisniListRequest request);

  List<UpisniListStatusDto> dohvatiUpisniListStatuse(String upisSifra);

  void izbrisi(String sifra);

  void izbrisiPoKorisniku(String korisnikSifra);

  void inicijalizirajUpisniList(Upis upis);
}
