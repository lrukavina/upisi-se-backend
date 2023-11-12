package hr.lrukavina.upisisebackend.model.kolegij;

import hr.lrukavina.upisisebackend.common.SifraOpis;
import hr.lrukavina.upisisebackend.common.SifraOpisHelper;
import hr.lrukavina.upisisebackend.exception.UpisiSeException;
import hr.lrukavina.upisisebackend.exception.VrstaPoruke;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijinfo.KolegijInfo;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijinfo.KolegijInfoManager;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik.KolegijNastavnik;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik.KolegijNastavnikManager;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik.request.AzurKolegijNastavnikRequest;
import hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik.request.SpremiKolegijNastavnikRequest;
import hr.lrukavina.upisisebackend.model.kolegij.request.AzurKolegijRequest;
import hr.lrukavina.upisisebackend.model.kolegij.request.SpremiKolegijRequest;
import hr.lrukavina.upisisebackend.model.kolegij.response.KolegijDto;
import hr.lrukavina.upisisebackend.model.kolegij.response.KolegijUpisniListDto;
import hr.lrukavina.upisisebackend.model.studij.Studij;
import hr.lrukavina.upisisebackend.model.studij.StudijManager;
import hr.lrukavina.upisisebackend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KolegijServiceImpl implements KolegijService {
  private final KolegijManager kolegijManager;
  private final KolegijInfoManager kolegijInfoManager;
  private final KolegijNastavnikManager kolegijNastavnikManager;
  private final StudijManager studijManager;
  private final SifraOpisHelper sifraOpisHelper;

  @Override
  public KolegijDto dohvati(String sifra) {
    Kolegij kolegij = kolegijManager.dohvati(Utils.desifrirajId(sifra));
    if (kolegij == null) {
      throw new UpisiSeException(VrstaPoruke.KOLEGIJ_NE_POSTOJI_U_BAZI);
    }
    KolegijInfo kolegijInfo = kolegijInfoManager.dohvatiPoKolegijId(kolegij.getId());

    List<KolegijNastavnik> kolegijNastavnici =
        kolegijNastavnikManager.dohvatiPoKolegijId(kolegij.getId());

    SifraOpis studij = sifraOpisHelper.dohvatiStudij(kolegij.getStudijId());

    return KolegijMapper.toDto(
        kolegij, studij, kolegijInfo, kolegijNastavnici, sifraOpisHelper.dohvatiKolegij(kolegij));
  }

  @Override
  @Transactional
  public KolegijDto spremi(SpremiKolegijRequest request) {
    Kolegij kolegij = KolegijMapper.pripremiSpremanjeZaKolegij(request);
    kolegijManager.spremi(kolegij);

    KolegijInfo kolegijInfo =
        KolegijMapper.prirpemiSpremanjeZaKolegijInfo(request.getKolegijInfo(), kolegij.getId());

    List<KolegijNastavnik> kolegijNastavnici =
        request.getNastavnici().stream()
            .map(
                nastavnikRequest ->
                    KolegijMapper.pripremiSpremanjeZaKolegijNastavnik(
                        nastavnikRequest, kolegij.getId()))
            .toList();

    kolegijInfoManager.spremi(kolegijInfo);
    kolegijNastavnici.forEach(kolegijNastavnikManager::spremi);

    SifraOpis studij = sifraOpisHelper.dohvatiStudij(kolegij.getStudijId());
    return KolegijMapper.toDto(
        kolegij, studij, kolegijInfo, kolegijNastavnici, sifraOpisHelper.dohvatiKolegij(kolegij));
  }

  @Override
  public List<KolegijUpisniListDto> dohvatiPoUpisniListId(Integer upisniListId) {
    List<Kolegij> kolegiji = kolegijManager.dohvatiPoUpisniListId(upisniListId);
    if (kolegiji.isEmpty()) {
      return Collections.emptyList();
    }
    Integer studijId = kolegiji.get(0).getStudijId();
    Studij studij = studijManager.dohvati(studijId);
    if (studij == null) {
      throw new UpisiSeException(VrstaPoruke.STUDIJ_NE_POSTOJI_U_BAZI);
    }
    return kolegiji.stream()
        .map(kolegij -> KolegijMapper.toKolegijUpisniListDto(kolegij, studij.getEctsCijena()))
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public KolegijDto azuriraj(AzurKolegijRequest request, String sifra) {
    Kolegij kolegij = kolegijManager.dohvati(Utils.desifrirajId(sifra));
    if (kolegij == null) {
      throw new UpisiSeException(VrstaPoruke.KOLEGIJ_NE_POSTOJI_U_BAZI);
    }
    KolegijInfo kolegijInfo = kolegijInfoManager.dohvatiPoKolegijId(kolegij.getId());

    List<KolegijNastavnik> kolegijNastavniciBaza =
        kolegijNastavnikManager.dohvatiPoKolegijId(kolegij.getId());

    SifraOpis studij = sifraOpisHelper.dohvatiStudij(kolegij.getStudijId());

    KolegijMapper.pripremiKolegijZaAzuriranje(request, kolegij);
    KolegijMapper.pripremiKolegijInfoZaAzuriranje(request.getKolegijInfo(), kolegijInfo);

    List<KolegijNastavnik> kolegijNastavnici = new ArrayList<>();
    for (AzurKolegijNastavnikRequest nastavnikRequest : request.getNastavnici()) {
      KolegijNastavnik kolegijNastavnik;
      Integer kolegijNastavnikId = Utils.desifrirajId(nastavnikRequest.getSifra());

      if (kolegijNastavnikId == null) {
        kolegijNastavnik =
            spremiKolegijNastavnik(
                KolegijMapper.azurKolegijNastavnikToSpremiRequest(nastavnikRequest),
                kolegij.getId());
      } else {
        kolegijNastavnik =
            azurirajKolegijNastavnik(nastavnikRequest, kolegijNastavniciBaza, kolegijNastavnikId);
      }
      kolegijNastavnici.add(kolegijNastavnik);
    }

    return KolegijMapper.toDto(
        kolegij, studij, kolegijInfo, kolegijNastavnici, sifraOpisHelper.dohvatiKolegij(kolegij));
  }

  private KolegijNastavnik azurirajKolegijNastavnik(
      AzurKolegijNastavnikRequest request,
      List<KolegijNastavnik> kolegijNastavnici,
      Integer kolegijNastavnikId) {

    KolegijNastavnik kolegijNastavnik =
        kolegijNastavnici.stream()
            .filter(nastavnik -> kolegijNastavnikId.equals(nastavnik.getId()))
            .findFirst()
            .orElse(null);

    if (kolegijNastavnik == null) {
      throw new UpisiSeException(VrstaPoruke.NASTAVNIK_NE_POSTOJI_U_BAZI);
    }
    KolegijMapper.pripremiKolegijNastavnikZaAzuriranje(request, kolegijNastavnik);
    kolegijNastavnikManager.azuriraj(kolegijNastavnik);
    return kolegijNastavnik;
  }

  private KolegijNastavnik spremiKolegijNastavnik(
      SpremiKolegijNastavnikRequest request, Integer kolegijId) {
    KolegijNastavnik kolegijNastavnik =
        KolegijMapper.pripremiSpremanjeZaKolegijNastavnik(request, kolegijId);
    kolegijNastavnikManager.spremi(kolegijNastavnik);
    return kolegijNastavnik;
  }

  @Override
  public void izbrisi(String sifra) {
    kolegijManager.izbrisi(Utils.desifrirajId(sifra));
  }
}
