package hr.lrukavina.upisisebackend.model.upis.response;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.common.SifraOpisKolegij;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UpisDto {
  private String sifra;
  private SifraOpis visokoUciliste;
  private SifraOpis studij;
  private Integer semestar;
  private Integer minBrojEctsa;
  private Integer maxBrojEctsa;
  private String datumVrijemeOd;
  private String datumVrijemeDo;
  private String status;
  private List<SifraOpisKolegij> obavezniKolegiji;
  private List<SifraOpisKolegij> izborniKolegiji;
}
