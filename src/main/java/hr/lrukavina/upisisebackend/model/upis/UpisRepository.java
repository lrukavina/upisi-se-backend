package hr.lrukavina.upisisebackend.model.upis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UpisRepository {
  Upis dohvati(Integer upisId);

  List<Upis> dohvatiSve();

  Upis dohvatiAktivneZaKorisnika(String korisnickoIme);

  Upis dohvatiPoSemestruZaStudij(
      @Param("semestar") Integer semestar, @Param("studijId") Integer studijId);

  void spremi(Upis upis);

  void azuriraj(Upis upis);

  void izbrisi(Integer upisId);
}
