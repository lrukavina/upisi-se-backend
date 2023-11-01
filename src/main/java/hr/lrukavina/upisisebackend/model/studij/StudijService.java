package hr.lrukavina.upisisebackend.model.studij;

import hr.lrukavina.upisisebackend.model.studij.request.AzurStudijRequest;
import hr.lrukavina.upisisebackend.model.studij.request.SpremiStudijRequest;
import hr.lrukavina.upisisebackend.model.studij.response.StudijDto;

public interface StudijService {
  StudijDto dohvati(String sifra);

  StudijDto spremi(SpremiStudijRequest request);

  StudijDto azuriraj(AzurStudijRequest request, String sifra);

  void izbrisi(String sifra);
}
