package hr.lrukavina.upisisebackend.model.upis;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UpisRepository {
  Upis dohvati(Integer upisId);

  void spremi(Upis upis);

  void azuriraj(Upis upis);

  void izbrisi(Integer upisId);
}
