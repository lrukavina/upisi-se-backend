package hr.lrukavina.upisisebackend.model.kolegij.request;

import hr.lrukavina.upisisebackend.model.kolegij.kolegijinfo.request.SpremiKolegijInfoRequest;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik.request.SpremiKolegijNastavnikRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpremiKolegijRequest {
  private String naziv;
  private Integer ects;
  private Integer semestar;
  private String isvuSifra;
  private boolean obavezan;
  private String studijSifra;
  private SpremiKolegijInfoRequest kolegijInfo;
  private List<SpremiKolegijNastavnikRequest> nastavnici;
}
