package hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik;

import hr.lrukavina.upisisebackend.model.studij.request.AzurStudijRequest;
import hr.lrukavina.upisisebackend.model.studij.request.SpremiStudijRequest;
import hr.lrukavina.upisisebackend.model.studij.response.StudijDto;

public interface KolegijService {
    StudijDto dohvati(String sifra);

    StudijDto spremi(SpremiStudijRequest request);

    StudijDto azuriraj(AzurStudijRequest request, String sifra);

    void izbrisi(String sifra);
}
