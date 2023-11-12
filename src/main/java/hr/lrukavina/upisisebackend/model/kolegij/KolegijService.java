package hr.lrukavina.upisisebackend.model.kolegij;

import hr.lrukavina.upisisebackend.model.kolegij.request.AzurKolegijRequest;
import hr.lrukavina.upisisebackend.model.kolegij.request.SpremiKolegijRequest;
import hr.lrukavina.upisisebackend.model.kolegij.response.KolegijDto;
import hr.lrukavina.upisisebackend.model.kolegij.response.KolegijUpisniListDto;

import java.util.List;

public interface KolegijService {
  KolegijDto dohvati(String sifra);

  KolegijDto spremi(SpremiKolegijRequest request);

  List<KolegijUpisniListDto> dohvatiPoUpisniListId(Integer upisniListId);

  KolegijDto azuriraj(AzurKolegijRequest request, String sifra);

  void izbrisi(String sifra);
}
