package hr.lrukavina.upisisebackend.model.kolegij;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface KolegijRepository {
  Kolegij dohvati(Integer kolegijId);

  void spremi(Kolegij kolegij);

  void azuriraj(Kolegij kolegij);

  void izbrisi(Integer kolegijId);
}
