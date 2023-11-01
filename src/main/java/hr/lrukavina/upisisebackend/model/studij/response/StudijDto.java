package hr.lrukavina.upisisebackend.model.studij.response;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudijDto {
    private String sifra;
    private String nazivStudija;
    private String nazivSmjera;
    private String ectsCijena;
    private SifraOpis visokoUciliste;
}
