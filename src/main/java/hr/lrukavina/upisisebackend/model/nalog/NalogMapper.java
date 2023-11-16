package hr.lrukavina.upisisebackend.model.nalog;

import hr.lrukavina.upisisebackend.utils.Konstante;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class NalogMapper {
  private NalogMapper() {}

  public static String toBarkod(Nalog nalog) throws UnsupportedEncodingException {
    String barkod =
        nalog.getZaglavlje()
            + Konstante.NOVI_RED
            + nalog.getValuta()
            + Konstante.NOVI_RED
            + nalog.getIznosBarkod()
            + Konstante.NOVI_RED
            + nalog.getPlatitelj()
            + Konstante.NOVI_RED
            + nalog.getAdresaPlatitelja().dohvatiUlicuKucniBr()
            + Konstante.NOVI_RED
            + nalog.getAdresaPlatitelja().dohvatiPostanskiBrMjesto()
            + Konstante.NOVI_RED
            + nalog.getPrimatelj()
            + Konstante.NOVI_RED
            + nalog.getAdresaPrimatelja().dohvatiUlicuKucniBr()
            + Konstante.NOVI_RED
            + nalog.getAdresaPrimatelja().dohvatiPostanskiBrMjesto()
            + Konstante.NOVI_RED
            + nalog.getIbanPrimatelja()
            + Konstante.NOVI_RED
            + nalog.getModelBarkod()
            + Konstante.NOVI_RED
            + nalog.getPozivNaBroj()
            + Konstante.NOVI_RED
            + nalog.getOpis();

    return kovertirajUHRznakove(barkod);
  }

  private static String kovertirajUHRznakove(String barkod) throws UnsupportedEncodingException {
    return new String(barkod.getBytes(StandardCharsets.UTF_8), Konstante.HR_CHARSET);
  }
}
