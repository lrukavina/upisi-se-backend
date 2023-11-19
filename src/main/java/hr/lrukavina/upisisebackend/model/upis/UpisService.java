package hr.lrukavina.upisisebackend.model.upis;

import hr.lrukavina.upisisebackend.model.upis.request.AzurUpisRequest;
import hr.lrukavina.upisisebackend.model.upis.request.SpremiUpisRequest;
import hr.lrukavina.upisisebackend.model.upis.response.UpisDto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public interface UpisService {
  UpisDto dohvati(String sifra);

  List<UpisDto> dohvatiSve();

  UpisDto dohvatiAktivneZaKorisnika(String korisnickoIme);

  UpisDto spremi(SpremiUpisRequest request);

  UpisDto azuriraj(AzurUpisRequest request, String sifra);

  ByteArrayOutputStream dohvatiPregledUpisa(String korisnickoIme) throws IOException;

  void izbrisi(String sifra);
}
