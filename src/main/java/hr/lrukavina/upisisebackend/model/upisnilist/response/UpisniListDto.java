package hr.lrukavina.upisisebackend.model.upisnilist.response;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.model.upisnilist.UpisniStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpisniListDto {
  private String sifra;
  private Integer brojEctsa;
  private String cijenaPoEctsu;
  private String ukupnaCijena;
  private String upisniBroj;
  private UpisniStatus status;
  private String upisSifra;
  private String korisnikSifra;
  private SifraOpis korisnik;
}
