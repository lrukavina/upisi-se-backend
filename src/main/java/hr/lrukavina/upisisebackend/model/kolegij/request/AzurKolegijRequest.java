package hr.lrukavina.upisisebackend.model.kolegij.request;

import hr.lrukavina.upisisebackend.model.kolegij.kolegijinfo.request.AzurKolegijInfoRequest;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik.request.AzurKolegijNastavnikRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AzurKolegijRequest {
  private String naziv;
  private Integer ects;
  private Integer semestar;
  private String isvuSifra;
  private boolean obavezan;
  private String studijSifra;
  private AzurKolegijInfoRequest kolegijInfo;
  private List<AzurKolegijNastavnikRequest> nastavnici;
}
