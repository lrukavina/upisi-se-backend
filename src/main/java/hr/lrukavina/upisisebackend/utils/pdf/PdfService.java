package hr.lrukavina.upisisebackend.utils.pdf;

import hr.lrukavina.upisisebackend.model.upisnilist.UpisniList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface PdfService {
  String generirajPdfPrivitak(UpisniList upisniList);

  ByteArrayOutputStream generirajPdfPrikaz(UpisniList upisniList) throws IOException;
}
