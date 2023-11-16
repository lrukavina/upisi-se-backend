package hr.lrukavina.upisisebackend.model.nalog;

import hr.lrukavina.upisisebackend.model.korisnik.Korisnik;
import hr.lrukavina.upisisebackend.model.korisnik.KorisnikManager;
import hr.lrukavina.upisisebackend.model.upisnilist.UpisniList;
import hr.lrukavina.upisisebackend.model.visokouciliste.VisokoUciliste;
import hr.lrukavina.upisisebackend.model.visokouciliste.VisokoUcilisteManager;
import hr.lrukavina.upisisebackend.utils.Konstante;
import hr.lrukavina.upisisebackend.utils.barkod.BarkodService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NalogServiceImpl implements NalogService {
  private final KorisnikManager korisnikManager;
  private final VisokoUcilisteManager visokoUcilisteManager;
  private final BarkodService barkodService;

  private static final String HUB3_PUTANJA =
      Konstante.RESURSI_PUTANJA + Konstante.NALOG_NAZIV + Konstante.TOCKA + Konstante.NALOG_FORMAT;

  private static final int IZNOS_X_DEFAULT = 1440;

  @Override
  public Nalog generirajZaUpisniList(UpisniList upisniList) {
    Korisnik korisnik = korisnikManager.dohvatiPoUpisniListId(upisniList.getId());
    VisokoUciliste visokoUciliste = visokoUcilisteManager.dohvatiPoUpisniListId(upisniList.getId());

    return Nalog.builder()
        .zaglavlje(Konstante.NALOG_ZAGLAVLJE)
        .valuta(Konstante.NALOG_VALUTA)
        .iznos(formatirajIznos(upisniList.getUkupnaCijena(), false))
        .iznosBarkod(formatirajIznos(upisniList.getUkupnaCijena(), true))
        .platitelj(korisnik.getImePrezime())
        .adresaPlatitelja(blankAdresa())
        .primatelj(visokoUciliste.getNaziv())
        .adresaPrimatelja(dohvatiAdresu(visokoUciliste))
        .ibanPrimatelja(visokoUciliste.getIban())
        .modelDrzava(Konstante.NALOG_MODEL_DRZAVA)
        .modelBroj(Konstante.NALOG_MODEL_BROJ)
        .pozivNaBroj(generirajPozivNaBroj(korisnik.getJmbag()))
        .sifraNamjene(Konstante.NALOG_SIF_NAMJENE)
        .opis(generirajOpis(upisniList.getUpisniBroj(), korisnik.getSemestar()))
        .build();
  }

  private String formatirajIznos(BigDecimal iznos, boolean isBarkod) {
    iznos = iznos.setScale(2, RoundingMode.CEILING);
    String formatted =
        StringUtils.leftPad(String.valueOf(iznos), 16, isBarkod ? "0" : Konstante.RAZMAK);
    return formatted.replace('.', ',');
  }

  private Adresa blankAdresa() {
    return Adresa.builder()
        .ulica(Konstante.PRAZAN_ZNAK)
        .kucniBroj(Konstante.PRAZAN_ZNAK)
        .postanskiBroj(Konstante.PRAZAN_ZNAK)
        .mjesto(Konstante.PRAZAN_ZNAK)
        .build();
  }

  private Adresa dohvatiAdresu(VisokoUciliste visokoUciliste) {
    String ulica = visokoUciliste.getAdresa().replaceAll("[^a-zA-Z].*", "");
    String kucniBroj = visokoUciliste.getAdresa().replaceAll("[^0-9]", "");
    return Adresa.builder()
        .ulica(ulica)
        .kucniBroj(kucniBroj)
        .postanskiBroj(visokoUciliste.getPostanskiBroj())
        .mjesto(visokoUciliste.getMjesto())
        .build();
  }

  private String generirajPozivNaBroj(String jmbag) {
    return Konstante.POZIV_NA_BROJ_PREFIX
        + Konstante.CRTA
        + jmbag
        + Konstante.CRTA
        + Konstante.POZIV_NA_BROJ_SUFIX;
  }

  private String generirajOpis(String upisniBroj, Integer semestar) {
    return "Školarina"
        + Konstante.RAZMAK
        + LocalDate.now().getYear()
        + Konstante.KOSA_CRTA
        + LocalDate.now().plusYears(1).getYear()
        + Konstante.RAZMAK
        + dohvatiOpisZaSemestar(semestar)
        + Konstante.RAZMAK
        + "SIF: "
        + upisniBroj;
  }

  private String dohvatiOpisZaSemestar(Integer semestar) {
    if (semestar % 2 == 0) {
      return Konstante.NALOG_LJETNI_SEM;
    }
    return Konstante.NALOG_ZIMSKI_SEM;
  }

  @Override
  public String generirajHub3(Nalog nalog, String upisniBroj) throws IOException {
    String barkodPutanja = barkodService.generirajBarkod(nalog);

    final BufferedImage hub3 = ImageIO.read(new File(HUB3_PUTANJA));
    final BufferedImage barkod = ImageIO.read(new File(barkodPutanja));

    Graphics g = hub3.getGraphics();
    g.setFont(new Font("Arial", Font.BOLD, 30));
    g.setColor(Color.BLACK);
    g.drawString(nalog.getPlatitelj(), 50, 130);
    g.drawString(Nalog.dodajRazmake(nalog.getValuta()), 810, 95);
    g.drawString(Nalog.dodajRazmake(nalog.getIznos()), 1130, 95);
    g.drawString(Nalog.dodajRazmake(nalog.getIbanPrimatelja()), 870, 310);
    g.drawString(Nalog.dodajRazmake(nalog.getModelDrzava()), 555, 400);
    g.drawString(Nalog.dodajRazmake(nalog.getModelBroj()), 630, 400);
    g.drawString(Nalog.dodajRazmake(nalog.getPozivNaBroj()), 1055, 400);
    g.drawString(nalog.getPrimatelj(), 50, 420);
    g.drawString(nalog.getAdresaPrimatelja().formatirajZaNalog(), 50, 460);
    g.drawString(Nalog.dodajRazmake(nalog.getSifraNamjene()), 550, 495);

    List<String> opisRetci = Nalog.prelomi(35, nalog.getOpis());
    int opisY = 450;
    for (String redak : opisRetci) {
      g.drawString(redak, 950, opisY);
      opisY += 40;
    }

    g.drawImage(barkod, 44, 660, null);
    g.dispose();

    String nazivDatoteke = generirajNaziv(upisniBroj);
    ImageIO.write(hub3, "jpg", new File(nazivDatoteke));
    return nazivDatoteke;
  }

  private int izracunajIznosX(String iznos) {
    return IZNOS_X_DEFAULT;
  }

  private String generirajNaziv(String upisniBroj) {
    return Konstante.RESURSI_PUTANJA
        + Konstante.NALOG_NAZIV
        + upisniBroj
        + Konstante.TOCKA
        + Konstante.NALOG_FORMAT;
  }
}
