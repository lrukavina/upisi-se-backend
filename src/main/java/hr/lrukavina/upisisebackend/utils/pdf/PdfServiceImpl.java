package hr.lrukavina.upisisebackend.utils.pdf;

import com.itextpdf.html2pdf.HtmlConverter;
import hr.lrukavina.upisisebackend.model.nalog.Nalog;
import hr.lrukavina.upisisebackend.model.nalog.NalogService;
import hr.lrukavina.upisisebackend.model.ugovor.Ugovor;
import hr.lrukavina.upisisebackend.model.ugovor.UgovorService;
import hr.lrukavina.upisisebackend.model.upisnilist.UpisniList;
import hr.lrukavina.upisisebackend.utils.barkod.BarkodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {
  private final UgovorService ugovorService;
  private final NalogService nalogService;
  private final BarkodService barkodService;
  private final PdfHelper pdfHelper;

  @Override
  public String generirajPdfPrivitak(UpisniList upisniList) {
    // todo implementirati
    return null;
  }

  @Override
  public ByteArrayOutputStream generirajPdfPrikaz(UpisniList upisniList) throws IOException {
    Ugovor ugovor = ugovorService.generirajZaUpisniList(upisniList);
    Nalog nalog = nalogService.generirajZaUpisniList(upisniList);
    String barkodPutanja = barkodService.generirajBarkod(nalog);

    String html = pdfHelper.generirajHtml(ugovor, nalog, barkodPutanja);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    HtmlConverter.convertToPdf(html, outputStream);
    return outputStream;
  }
}
