package hr.lrukavina.upisisebackend.model.kolegij.kolegijinfo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface KolegijInfoRepository {
  KolegijInfo dohvati(Integer kolegijInfoId);

  KolegijInfo dohvatiPoKolegijId(Integer kolegijId);

  void spremi(KolegijInfo kolegijInfo);

  void azuriraj(KolegijInfo kolegijInfo);

  void izbrisi(Integer kolegijInfoId);
}
