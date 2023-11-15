package hr.lrukavina.upisisebackend.utils.barkod;

import hr.lrukavina.upisisebackend.model.nalog.Nalog;
import hr.lrukavina.upisisebackend.model.nalog.NalogMapper;
import hr.lrukavina.upisisebackend.utils.Konstante;
import lombok.RequiredArgsConstructor;
import org.krysalis.barcode4j.impl.pdf417.PDF417Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class BarkodServiceImpl implements BarkodService {
  @Override
  public String generirajBarkod(Nalog nalog) throws IOException {
    PDF417Bean barcodeGenerator = new PDF417Bean();
    BitmapCanvasProvider canvas =
        new BitmapCanvasProvider(200, BufferedImage.TYPE_BYTE_BINARY, false, 0);
    barcodeGenerator.setColumns(9);

    barcodeGenerator.generateBarcode(canvas, NalogMapper.toBarkod(nalog));
    ImageIO.write(
        canvas.getBufferedImage(),
        Konstante.BARKOD_FORMAT.toUpperCase(Locale.ROOT),
        new File(puniNazivBarkoda()));
    return puniNazivBarkoda();
  }

  private String puniNazivBarkoda() {
    return Konstante.RESURSI_PUTANJA
        + Konstante.BARKOD_NAZIV
        + Konstante.TOCKA
        + Konstante.BARKOD_FORMAT;
  }
}
