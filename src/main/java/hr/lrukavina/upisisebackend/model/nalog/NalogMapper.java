package hr.lrukavina.upisisebackend.model.nalog;

import hr.lrukavina.upisisebackend.utils.Konstante;

public class NalogMapper {
  private NalogMapper() {}

  public static String toBarkod(Nalog nalog) {
    return nalog.getZaglavlje()
        + Konstante.NOVI_RED
        + nalog.getValuta()
        + Konstante.NOVI_RED
        + nalog.getIznos()
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
        + nalog.getModel()
        + Konstante.NOVI_RED
        + nalog.getPozivNaBroj()
        + Konstante.NOVI_RED
        + nalog.getOpis();
  }
}
