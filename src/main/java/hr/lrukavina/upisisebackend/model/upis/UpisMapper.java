package hr.lrukavina.upisisebackend.model.upis;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.model.upis.request.AzurUpisRequest;
import hr.lrukavina.upisisebackend.model.upis.request.SpremiUpisRequest;
import hr.lrukavina.upisisebackend.model.upis.response.UpisDto;
import hr.lrukavina.upisisebackend.model.upisnilist.UpisniStatus;
import hr.lrukavina.upisisebackend.utils.Utils;
import org.springframework.beans.BeanUtils;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class UpisMapper {
  private UpisMapper() {}

  public static UpisDto toDto(
      Upis upis,
      SifraOpis visokoUciliste,
      SifraOpis studij,
      List<SifraOpis> obavezniKolegiji,
      List<SifraOpis> izbroniKolegiji,
      UpisniStatus status) {

    String datumVrijemeOd =
        upis.getTstampOd().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            + " "
            + upis.getTstampOd().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

    String datumVrijemeDo =
        upis.getTstampDo().toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            + " "
            + upis.getTstampDo().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

    return UpisDto.builder()
        .sifra(Utils.sifrirajId(upis.getId()))
        .visokoUciliste(visokoUciliste)
        .studij(studij)
        .semestar(upis.getSemestar())
        .minBrojEctsa(upis.getMinBrojEctsa())
        .maxBrojEctsa(upis.getMaxBrojEctsa())
        .datumVrijemeOd(datumVrijemeOd)
        .datumVrijemeDo(datumVrijemeDo)
        .status(status != null ? String.valueOf(status) : null)
        .obavezniKolegiji(obavezniKolegiji)
        .izborniKolegiji(izbroniKolegiji)
        .build();
  }

  public static Upis pripremiSpremanje(SpremiUpisRequest request) {
    return Upis.builder()
        .semestar(request.getSemestar())
        .minBrojEctsa(request.getMinBrojEctsa())
        .maxBrojEctsa(request.getMaxBrojEctsa())
        .tstampOd(request.getTstampOd())
        .tstampDo(request.getTstampDo())
        .studijId(Utils.desifrirajId(request.getStudijSifra()))
        .build();
  }

  public static void pripremiZaAzuriranje(AzurUpisRequest request, Upis upis) {
    Upis upisRequest =
        Upis.builder()
            .semestar(request.getSemestar())
            .minBrojEctsa(request.getMinBrojEctsa())
            .maxBrojEctsa(request.getMaxBrojEctsa())
            .tstampOd(request.getTstampOd())
            .tstampDo(request.getTstampDo())
            .studijId(Utils.desifrirajId(request.getStudijSifra()))
            .build();

    BeanUtils.copyProperties(upisRequest, upis, Utils.ignoreNullFieldove(upisRequest));
  }
}