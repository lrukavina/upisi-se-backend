package hr.lrukavina.upisisebackend.model.studij;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.model.studij.request.AzurStudijRequest;
import hr.lrukavina.upisisebackend.model.studij.request.SpremiStudijRequest;
import hr.lrukavina.upisisebackend.model.studij.response.StudijDto;
import hr.lrukavina.upisisebackend.utils.Konstante;
import hr.lrukavina.upisisebackend.utils.Utils;
import org.springframework.beans.BeanUtils;

import java.text.NumberFormat;

public class StudijMapper {
  private StudijMapper() {}

  public static StudijDto toDto(Studij studij, SifraOpis visokoUciliste) {
    return StudijDto.builder()
        .sifra(Utils.sifrirajId(studij.getId()))
        .nazivStudija(studij.getNazivStudija())
        .nazivSmjera(studij.getNazivSmjera())
        .ectsCijena(studij.getEctsCijena())
        .ectsCijenaFormatirana(
            NumberFormat.getCurrencyInstance(Konstante.LOCALE_VALUTA)
                .format(studij.getEctsCijena()))
        .visokoUciliste(visokoUciliste)
        .build();
  }

  public static Studij pripremiSpremanje(SpremiStudijRequest request) {
    return Studij.builder()
        .nazivStudija(request.getNazivStudija())
        .nazivSmjera(request.getNazivSmjera())
        .ectsCijena(request.getEctsCijena())
        .visokoUcilisteId(Utils.desifrirajId(request.getVisokoUcilisteSifra()))
        .build();
  }

  public static void pripremiZaAzuriranje(AzurStudijRequest request, Studij studij) {
    Studij studijRequest =
        Studij.builder()
            .nazivStudija(request.getNazivStudija())
            .nazivSmjera(request.getNazivSmjera())
            .ectsCijena(request.getEctsCijena())
            .visokoUcilisteId(Utils.desifrirajId(request.getVisokoUcilisteSifra()))
            .build();

    BeanUtils.copyProperties(studijRequest, studij, Utils.ignoreNullFieldove(studijRequest));
  }
}
