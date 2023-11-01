package hr.lrukavina.upisisebackend.model.visokouciliste;

import hr.lrukavina.upisisebackend.model.visokouciliste.request.AzurVisokoUcilisteRequest;
import hr.lrukavina.upisisebackend.model.visokouciliste.request.SpremiVisokoUcilisteRequest;
import hr.lrukavina.upisisebackend.model.visokouciliste.response.VisokoUcilisteDto;
import hr.lrukavina.upisisebackend.utils.Utils;
import org.springframework.beans.BeanUtils;

public class VisokoUcilisteMapper {
  private VisokoUcilisteMapper() {}

  public static VisokoUcilisteDto toDto(VisokoUciliste visokoUciliste) {
    return VisokoUcilisteDto.builder()
        .naziv(visokoUciliste.getNaziv())
        .adresa(visokoUciliste.getAdresa())
        .postanskiBroj(visokoUciliste.getPostanskiBroj())
        .mjesto(visokoUciliste.getMjesto())
        .iban(visokoUciliste.getIban())
        .oib(visokoUciliste.getOib())
        .build();
  }

  public static VisokoUciliste pripremiSpremanje(SpremiVisokoUcilisteRequest request) {
    return VisokoUciliste.builder()
        .naziv(request.getNaziv())
        .adresa(request.getAdresa())
        .postanskiBroj(request.getPostanskiBroj())
        .mjesto(request.getMjesto())
        .iban(request.getIban())
        .oib(request.getOib())
        .build();
  }

  public static void pripremiZaAzuriranje(
      AzurVisokoUcilisteRequest request, VisokoUciliste visokoUciliste) {
    VisokoUciliste visokoUcilisteRequest =
        VisokoUciliste.builder()
            .naziv(request.getNaziv())
            .adresa(request.getAdresa())
            .postanskiBroj(request.getPostanskiBroj())
            .mjesto(request.getMjesto())
            .iban(request.getIban())
            .oib(request.getOib())
            .build();

    BeanUtils.copyProperties(
        visokoUcilisteRequest, visokoUciliste, Utils.ignoreNullFieldove(visokoUcilisteRequest));
  }
}
