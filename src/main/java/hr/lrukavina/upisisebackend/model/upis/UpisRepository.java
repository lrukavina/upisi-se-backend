package hr.lrukavina.upisisebackend.model.upis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UpisRepository {
  Upis dohvati(Integer upisId);

  Upis dohvatiAktivneZaKorisnika(String korisnickoIme);

  Upis dohvatiPoSemestruZaStudij(
      @Param("semestar") Integer semestar, @Param("studijId") Integer studijId);

  void spremi(Upis upis);

  void azuriraj(Upis upis);

  void izbrisi(Integer upisId);
}
