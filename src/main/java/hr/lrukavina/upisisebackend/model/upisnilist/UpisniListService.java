package hr.lrukavina.upisisebackend.model.upisnilist;

import hr.lrukavina.upisisebackend.model.upis.Upis;
import hr.lrukavina.upisisebackend.model.upisnilist.request.AzurUpisniListRequest;
import hr.lrukavina.upisisebackend.model.upisnilist.response.UpisniListDto;

public interface UpisniListService {
  UpisniListDto dohvati(String sifra);

  UpisniListDto dohvatiPoKorisniku(String korisnikSifra);

  UpisniListDto azuriraj(AzurUpisniListRequest request, String sifra);

  void izbrisi(String sifra);

  void inicijalizirajUpisniList(Upis upis);
}
