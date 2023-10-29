package hr.lrukavina.upisisebackend.model.korisnik;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface KorisnikRepository {
  Korisnik dohvatiPoKorisnickomImenu(String korisnickoIme);

  void spremi(Korisnik korisnik);
}
