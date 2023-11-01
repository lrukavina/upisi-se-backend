package hr.lrukavina.upisisebackend.model.visokouciliste;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VisokoUcilisteRepository {
  VisokoUciliste dohvati(Integer visokoUcilisteId);

  void spremi(VisokoUciliste visokoUciliste);

  void azuriraj(VisokoUciliste visokoUciliste);

  void izbrisi(Integer visokoUcilisteId);
}
