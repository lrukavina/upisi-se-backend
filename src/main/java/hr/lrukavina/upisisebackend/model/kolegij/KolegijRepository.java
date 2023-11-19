package hr.lrukavina.upisisebackend.model.kolegij;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface KolegijRepository {
  Kolegij dohvati(Integer kolegijId);

  List<Kolegij> dohvatiSve();

  List<Kolegij> dohvatiPoUpisId(Integer upisId);

  List<Kolegij> dohvatiPoUpisniListId(Integer upisniListId);

  void spremi(Kolegij kolegij);

  void azuriraj(Kolegij kolegij);

  void izbrisi(Integer kolegijId);
}
