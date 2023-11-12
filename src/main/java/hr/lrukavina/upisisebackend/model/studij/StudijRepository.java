package hr.lrukavina.upisisebackend.model.studij;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudijRepository {
  Studij dohvati(Integer studijId);

  Studij dohvatiPoUpisniListId(Integer upisniListId);

  void spremi(Studij studij);

  void azuriraj(Studij studij);

  void izbrisi(Integer studijId);
}
