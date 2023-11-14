package hr.lrukavina.upisisebackend.utils.pdf;

import hr.lrukavina.upisisebackend.model.nalog.Nalog;
import hr.lrukavina.upisisebackend.model.ugovor.Ugovor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {
  @Override
  public String generirajPdfPrivitak(Ugovor ugovor, Nalog nalog, String barkodPutanja) {
    // todo implementirati
    return null;
  }

  @Override
  public ByteArrayOutputStream generirajPdfPrikaz(
      Ugovor ugovor, Nalog nalog, String barkodPutanja) {
    // todo implementirati
    return null;
  }
}
