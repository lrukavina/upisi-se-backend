package hr.lrukavina.upisisebackend.model.upis;

import hr.lrukavina.upisisebackend.exception.UpisiSeException;
import hr.lrukavina.upisisebackend.exception.VrstaPoruke;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpisValidator {
  private final UpisManager manager;

  public void validirajSpremanje(Upis upis) {
    if (manager.postojiUpisPoSemestruZaStudij(upis)) {
      throw new UpisiSeException(VrstaPoruke.UPIS_VEC_POSTOJI_U_BAZI);
    }
  }
}
