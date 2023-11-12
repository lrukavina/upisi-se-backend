package hr.lrukavina.upisisebackend.model.upisnilist;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UpisniListRepository {
  UpisniList dohvati(Integer upisniListId);

  UpisniList dohvatiPoKorisnikId(Integer korisnikId);

  UpisniList dohvatiPoKorisniku(String korisnickoIme);

  List<UpisniList> dohvatiPoUpisId(Integer upisId);

  void spremi(UpisniList upisniList);

  void azuriraj(UpisniList upisniList);

  void izbrisi(Integer upisniListId);
}
