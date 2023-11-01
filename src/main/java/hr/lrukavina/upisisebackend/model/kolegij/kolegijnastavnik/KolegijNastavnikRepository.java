package hr.lrukavina.upisisebackend.model.kolegij.kolegijnastavnik;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface KolegijNastavnikRepository {
  KolegijNastavnik dohvati(Integer kolegijNastavnikId);

  void spremi(KolegijNastavnik kolegijNastavnik);

  void azuriraj(KolegijNastavnik kolegijNastavnik);

  void izbrisi(Integer kolegijNastavnikId);
}
