package hr.lrukavina.upisisebackend.model.kolegij.response;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijinfo.response.KolegijInfoDto;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik.response.KolegijNastavnikDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class KolegijDto {
  private String sifra;
  private String naziv;
  private Integer ects;
  private Integer semestar;
  private String isvuSifra;
  private boolean obavezan;
  private SifraOpis studij;
  private SifraOpis visokoUciliste;
  private KolegijInfoDto kolegijInfo;
  private List<KolegijNastavnikDto> nastavnici;
}
