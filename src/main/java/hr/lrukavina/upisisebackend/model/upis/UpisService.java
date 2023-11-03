package hr.lrukavina.upisisebackend.model.upis;

import hr.lrukavina.upisisebackend.model.upis.request.AzurUpisRequest;
import hr.lrukavina.upisisebackend.model.upis.request.SpremiUpisRequest;
import hr.lrukavina.upisisebackend.model.upis.response.UpisDto;

public interface UpisService {
  UpisDto dohvati(String sifra);

  UpisDto spremi(SpremiUpisRequest request);

  UpisDto azuriraj(AzurUpisRequest request, String sifra);

  void izbrisi(String sifra);
}
