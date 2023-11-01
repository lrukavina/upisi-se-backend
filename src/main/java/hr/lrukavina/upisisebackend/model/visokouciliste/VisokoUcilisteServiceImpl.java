package hr.lrukavina.upisisebackend.model.visokouciliste;

import hr.lrukavina.upisisebackend.model.visokouciliste.request.AzurVisokoUcilisteRequest;
import hr.lrukavina.upisisebackend.model.visokouciliste.request.SpremiVisokoUcilisteRequest;
import hr.lrukavina.upisisebackend.model.visokouciliste.response.VisokoUcilisteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VisokoUcilisteServiceImpl implements VisokoUcilisteService {
  @Override
  public VisokoUcilisteDto dohvati(Integer visokoUcilisteId) {
    return null;
  }

  @Override
  @Transactional
  public void spremi(SpremiVisokoUcilisteRequest visokoUciliste) {}

  @Override
  @Transactional
  public void azuriraj(AzurVisokoUcilisteRequest visokoUciliste) {}

  @Override
  public void izbrisi(Integer visokoUcilisteId) {}
}
