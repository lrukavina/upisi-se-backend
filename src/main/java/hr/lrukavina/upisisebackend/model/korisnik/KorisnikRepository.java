package hr.lrukavina.upisisebackend.model.korisnik;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface KorisnikRepository {
  Korisnik dohvati(Integer korisnikId);

  Korisnik dohvatiPoKorisnickomImenu(String korisnickoIme);

  Korisnik dohvatiZadnjeg(String korisnickoIme);

  List<Korisnik> dohvatiPoSemestru(Integer semestar);

  void spremi(Korisnik korisnik);

  void azuriraj(Korisnik korisnik);

  void izbrisiPoKorisnickomImenu(String korisnickoIme);

  void izbrisi(Integer korisnikId);
}
