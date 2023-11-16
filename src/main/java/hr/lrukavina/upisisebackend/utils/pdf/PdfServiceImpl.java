package hr.lrukavina.upisisebackend.utils.pdf;

import com.itextpdf.html2pdf.HtmlConverter;
import hr.lrukavina.upisisebackend.exception.UpisiSeException;
import hr.lrukavina.upisisebackend.exception.VrstaPoruke;
import hr.lrukavina.upisisebackend.model.nalog.Nalog;
import hr.lrukavina.upisisebackend.model.nalog.NalogService;
import hr.lrukavina.upisisebackend.model.nalog.PutanjaDatoteke;
import hr.lrukavina.upisisebackend.model.ugovor.Ugovor;
import hr.lrukavina.upisisebackend.model.ugovor.UgovorService;
import hr.lrukavina.upisisebackend.model.upisnilist.UpisniList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {
  private final UgovorService ugovorService;
  private final NalogService nalogService;
  private final PdfHelper pdfHelper;

  @Override
  public ByteArrayOutputStream generirajPdfPrikaz(UpisniList upisniList) throws IOException {
    Ugovor ugovor = ugovorService.generirajZaUpisniList(upisniList);
    Nalog nalog = nalogService.generirajZaUpisniList(upisniList);
    PutanjaDatoteke putanjaDatoteke = nalogService.generirajHub3(nalog, upisniList.getUpisniBroj());

    String html = pdfHelper.generirajHtml(ugovor, nalog, putanjaDatoteke.getHub3Putanja());

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    HtmlConverter.convertToPdf(html, outputStream);
    izbrisiPrivremeneDatoteke(putanjaDatoteke);
    return outputStream;
  }

  private void izbrisiPrivremeneDatoteke(PutanjaDatoteke putanjaDatoteke) {
    File barkod = new File(putanjaDatoteke.getBarkodPutanja());
    File hub3 = new File(putanjaDatoteke.getHub3Putanja());

    if (!barkod.delete() || !hub3.delete()) {
      throw new UpisiSeException(VrstaPoruke.DATOTEKA_NE_POSTOJI);
    }
  }
}
