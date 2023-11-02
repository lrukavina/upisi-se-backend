package hr.lrukavina.upisisebackend.model.kolegij;

import hr.lrukavina.upisisebackend.model.kolegij.request.AzurKolegijRequest;
import hr.lrukavina.upisisebackend.model.kolegij.request.SpremiKolegijRequest;
import hr.lrukavina.upisisebackend.model.kolegij.response.KolegijDto;

public interface KolegijService {
  KolegijDto dohvati(String sifra);

  KolegijDto spremi(SpremiKolegijRequest request);

  KolegijDto azuriraj(AzurKolegijRequest request, String sifra);

  void izbrisi(String sifra);
}
