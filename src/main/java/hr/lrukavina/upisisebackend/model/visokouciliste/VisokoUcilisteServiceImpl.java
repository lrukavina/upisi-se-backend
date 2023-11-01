package hr.lrukavina.upisisebackend.model.visokouciliste;

import hr.lrukavina.upisisebackend.exception.UpisiSeException;
import hr.lrukavina.upisisebackend.exception.VrstaPoruke;
import hr.lrukavina.upisisebackend.model.visokouciliste.request.AzurVisokoUcilisteRequest;
import hr.lrukavina.upisisebackend.model.visokouciliste.request.SpremiVisokoUcilisteRequest;
import hr.lrukavina.upisisebackend.model.visokouciliste.response.VisokoUcilisteDto;
import hr.lrukavina.upisisebackend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VisokoUcilisteServiceImpl implements VisokoUcilisteService {

  private final VisokoUcilisteManager manager;

  @Override
  public VisokoUcilisteDto dohvati(String sifra) {
    VisokoUciliste visokoUciliste = manager.dohvati(Utils.desifrirajId(sifra));
    if (visokoUciliste == null) {
      throw new UpisiSeException(VrstaPoruke.VISOKO_UCILISTE_NE_POSTOJI_U_BAZI);
    }
    return VisokoUcilisteMapper.toDto(visokoUciliste);
  }

  @Override
  @Transactional
  public void spremi(SpremiVisokoUcilisteRequest request) {
    VisokoUciliste visokoUciliste = VisokoUcilisteMapper.pripremiSpremanje(request);
    manager.spremi(visokoUciliste);
  }

  @Override
  @Transactional
  public VisokoUcilisteDto azuriraj(AzurVisokoUcilisteRequest request, String sifra) {
    VisokoUciliste visokoUciliste = manager.dohvati(Utils.desifrirajId(sifra));
    if (visokoUciliste == null) {
      throw new UpisiSeException(VrstaPoruke.VISOKO_UCILISTE_NE_POSTOJI_U_BAZI);
    }
    VisokoUcilisteMapper.pripremiZaAzuriranje(request, visokoUciliste);
    manager.azuriraj(visokoUciliste);
    return VisokoUcilisteMapper.toDto(visokoUciliste);
  }

  @Override
  public void izbrisi(String sifra) {
    manager.izbrisi(Utils.desifrirajId(sifra));
  }
}
