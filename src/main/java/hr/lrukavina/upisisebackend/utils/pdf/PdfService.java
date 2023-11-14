package hr.lrukavina.upisisebackend.utils.pdf;

import hr.lrukavina.upisisebackend.model.nalog.Nalog;
import hr.lrukavina.upisisebackend.model.ugovor.Ugovor;

import java.io.ByteArrayOutputStream;

public interface PdfService {
  String generirajPdfPrivitak(Ugovor ugovor, Nalog nalog, String barkodPutanja);

  ByteArrayOutputStream generirajPdfPrikaz(Ugovor ugovor, Nalog nalog, String barkodPutanja);
}
