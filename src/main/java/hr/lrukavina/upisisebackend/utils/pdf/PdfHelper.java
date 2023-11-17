package hr.lrukavina.upisisebackend.utils.pdf;

import hr.lrukavina.upisisebackend.model.nalog.Nalog;
import hr.lrukavina.upisisebackend.model.ugovor.Ugovor;
import hr.lrukavina.upisisebackend.model.ugovor.dto.UgovorKolegijDto;
import hr.lrukavina.upisisebackend.utils.Konstante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class PdfHelper {

  private static final String PRAZAN_REDAK = "<br>";
  private static final String PRAZAN_PARAGRAF = "<p>&nbsp; </p>";

  public String generirajHtml(Ugovor ugovor, Nalog nalog, String hub3Putanja) {
    StringBuilder html = new StringBuilder();

    html.append("<body style=\"font-family: \"times\">");

    html.append("<p style=\"text-align:center\"><strong>")
        .append(nalog.getPrimatelj())
        .append("</strong></p>");

    html.append("<p style=\"text-align:center\"><strong>")
        .append(ugovor.getNazivStudija())
        .append("(")
        .append(ugovor.getNazivSmjera())
        .append(")")
        .append("</strong></p>");

    html.append(PRAZAN_REDAK);

    html.append("<p>KLASA: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ")
        .append(ugovor.getKlasa())
        .append("</p>");

    html.append("<p>URBROJ: &nbsp; &nbsp; &nbsp; &nbsp; ")
        .append(ugovor.getUrbroj())
        .append("</p>");

    html.append("<p>Ime:&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ")
        .append(ugovor.getIme())
        .append("</p>");

    html.append("<p>Prezime:&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;")
        .append(ugovor.getPrezime())
        .append("</p>");

    html.append("<p>Jmbag:&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;")
        .append(ugovor.getJmbag())
        .append("</p>");

    html.append(
            "<p style=\"text-align:center\"><u><strong>UPISNI LIST I IZRAČUN &Scaron;KOLARINE ZA AKADEMSKU GODINU")
        .append(Konstante.RAZMAK)
        .append(LocalDate.now().getYear())
        .append(Konstante.KOSA_CRTA)
        .append(LocalDate.now().plusYears(1).getYear())
        .append("</strong></u></p>");

    html.append("<p>Semestar: ").append(ugovor.getSemestar()).append("</p>");

    html.append("<p>Upisani predmeti: ").append("</p>");

    html.append(
            "<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" style=\"margin-left:auto; margin-right:auto; text-align:center; width:500px\">")
        .append("<thead>")
        .append("<tr>")
        .append("<th scope=\"col\">&Scaron;ifra</th>")
        .append("<th scope=\"col\">Naziv</th>")
        .append("<th scope=\"col\">ECTS</th>")
        .append("</tr>")
        .append("</thead>")
        .append("<tbody>");

    Integer brojEctsa = 0;
    BigDecimal cijenaEctsa = BigDecimal.ZERO;
    for (UgovorKolegijDto kolegij : ugovor.getKolegiji()) {
      html.append("<tr>")
          .append("<td>")
          .append(kolegij.getIsvuSifra())
          .append("</td>")
          .append("<td>")
          .append(kolegij.getNaziv())
          .append("</td>")
          .append("<td>")
          .append(kolegij.getEcts())
          .append("</td>")
          .append("</tr>");

      brojEctsa += kolegij.getEcts();
      cijenaEctsa = kolegij.getEctsCijena();
    }

    html.append("</tbody>").append("</table>");

    BigDecimal ukupnaCIjena = cijenaEctsa.multiply(BigDecimal.valueOf(brojEctsa));
    html.append("<p>Broj upisanih ECTS bodova po datumu upisa: ")
        .append(brojEctsa)
        .append(" x ")
        .append(cijenaEctsa)
        .append(" = ")
        .append(ukupnaCIjena)
        .append(Konstante.RAZMAK)
        .append("&euro;&nbsp;</p>");

    html.append("<p>Iznos &scaron;kolarine student plaća na IBAN: ")
        .append("<b>")
        .append(nalog.getIbanPrimatelja())
        .append("</b>")
        .append(Konstante.RAZMAK)
        .append("s pozivom na broj odobrenja ")
        .append(nalog.getPozivNaBroj());

    html.append(
        "<p>Upisni list i izračun &scaron;kolarine sukladan je odluci Upravnog vijeća o iznosu &scaron;kolarine i ostalim tro&scaron;kovima koji proizlaze iz upisa, a koja se primjenjuje u ak. godini u kojoj se semestar upisuje kao i nastavnom planu i programu predviđenom za upisanu ak.godinu. Ovaj Upisni list i Izračun &scaron;kolarine sastavljen je u 2 (dva) jednaka primjerka: (1) jedan za studenta, a 1 (jedan) za Visoko učili&scaron;te</p>");

    html.append("<p>Student</p>");

    html.append("<p>")
        .append(ugovor.getIme())
        .append(Konstante.RAZMAK)
        .append(ugovor.getPrezime())
        .append("</p>");

    html.append("<p>__________________________</p>");

    html.append("<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; (potpis)</p>");

    html.append(PRAZAN_PARAGRAF);

    html.append("<p style=\"text-align:left\">Datum i mjesto izdavanja: <br>")
        .append(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")))
        .append(Konstante.ZAREZ)
        .append(Konstante.RAZMAK)
        .append(nalog.getAdresaPrimatelja().getMjesto())
        .append("</p>");

    html.append(PRAZAN_PARAGRAF).append(PRAZAN_PARAGRAF);
    html.append("<img src=\"").append(hub3Putanja).append("\" width=\"100%\" height=\"auto\" />");

    html.append("</body>");

    return html.toString();
  }
}
