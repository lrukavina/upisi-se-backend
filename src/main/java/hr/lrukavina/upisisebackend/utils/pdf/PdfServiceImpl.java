package hr.lrukavina.upisisebackend.utils.pdf;

import com.itextpdf.html2pdf.HtmlConverter;
import hr.lrukavina.upisisebackend.model.nalog.Nalog;
import hr.lrukavina.upisisebackend.model.nalog.NalogService;
import hr.lrukavina.upisisebackend.model.ugovor.Ugovor;
import hr.lrukavina.upisisebackend.model.ugovor.UgovorService;
import hr.lrukavina.upisisebackend.model.upisnilist.UpisniList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
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
    String hub3Putanja = nalogService.generirajHub3(nalog, upisniList.getUpisniBroj());

    String html = pdfHelper.generirajHtml(ugovor, nalog, hub3Putanja);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    HtmlConverter.convertToPdf(html, outputStream);

    return outputStream;
  }
}
