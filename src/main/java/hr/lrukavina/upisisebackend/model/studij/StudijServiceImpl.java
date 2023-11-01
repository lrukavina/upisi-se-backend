package hr.lrukavina.upisisebackend.model.studij;

import hr.lrukavina.upisisebackend.common.SifraOpisHelper;
import hr.lrukavina.upisisebackend.exception.UpisiSeException;
import hr.lrukavina.upisisebackend.exception.VrstaPoruke;
import hr.lrukavina.upisisebackend.model.studij.request.AzurStudijRequest;
import hr.lrukavina.upisisebackend.model.studij.request.SpremiStudijRequest;
import hr.lrukavina.upisisebackend.model.studij.response.StudijDto;
import hr.lrukavina.upisisebackend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudijServiceImpl implements StudijService {
  private final StudijManager manager;
  private final SifraOpisHelper sifraOpisHelper;

  @Override
  public StudijDto dohvati(String sifra) {
    Studij studij = manager.dohvati(Utils.desifrirajId(sifra));
    if (studij == null) {
      throw new UpisiSeException(VrstaPoruke.STUDIJ_NE_POSTOJI_U_BAZI);
    }
    return StudijMapper.toDto(
        studij, sifraOpisHelper.dohvatiVisokoUciliste(studij.getVisokoUcilisteId()));
  }

  @Override
  @Transactional
  public StudijDto spremi(SpremiStudijRequest request) {
    Studij studij = StudijMapper.pripremiSpremanje(request);
    manager.spremi(studij);
    return StudijMapper.toDto(
        studij, sifraOpisHelper.dohvatiVisokoUciliste(studij.getVisokoUcilisteId()));
  }

  @Override
  @Transactional
  public StudijDto azuriraj(AzurStudijRequest request, String sifra) {
    Studij studij = manager.dohvati(Utils.desifrirajId(sifra));
    if (studij == null) {
      throw new UpisiSeException(VrstaPoruke.STUDIJ_NE_POSTOJI_U_BAZI);
    }
    StudijMapper.pripremiZaAzuriranje(request, studij);
    manager.azuriraj(studij);
    return StudijMapper.toDto(
        studij, sifraOpisHelper.dohvatiVisokoUciliste(studij.getVisokoUcilisteId()));
  }

  @Override
  public void izbrisi(String sifra) {
    manager.izbrisi(Utils.desifrirajId(sifra));
  }
}
