package hr.lrukavina.upisisebackend.model.studij;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudijRepository {
  List<Studij> dohvatiSve();

  Studij dohvati(Integer studijId);

  Studij dohvatiPoUpisniListId(Integer upisniListId);

  List<Studij> dohvatiPoVisokoUcilisteId(Integer visokoUcilisteId);

  void spremi(Studij studij);

  void azuriraj(Studij studij);

  void izbrisi(Integer studijId);
}
