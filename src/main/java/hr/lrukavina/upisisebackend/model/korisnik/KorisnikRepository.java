package hr.lrukavina.upisisebackend.model.korisnik;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface KorisnikRepository {
  Korisnik dohvati(Integer korisnikId);

  Korisnik dohvatiPoKorisnickomImenu(String korisnickoIme);

  void spremi(Korisnik korisnik);

  void azuriraj(Korisnik korisnik);

  void izbrisiPoKorisnickomImenu(String korisnickoIme);

  void izbrisi(Integer korisnikId);
}
