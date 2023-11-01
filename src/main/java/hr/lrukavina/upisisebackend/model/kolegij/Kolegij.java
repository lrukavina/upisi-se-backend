package hr.lrukavina.upisisebackend.model.kolegij;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Kolegij {
  private Integer id;
  private String naziv;
  private Integer ects;
  private Integer semestar;
  private String isvuSifra;
  private boolean obavezan;
  private Integer studijId;
  private KolegijInfo kolegijInfo;
  private List<KolegijNastavnik> nastavnici;
}
