package hr.lrukavina.upisisebackend.model.visokouciliste;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VisokoUcilisteRepository {
  VisokoUciliste dohvati(Integer visokoUcilisteId);

  List<VisokoUciliste> dohvatiSve();

  VisokoUciliste dohvatiPoUpisniListId(Integer upisniListId);

  void spremi(VisokoUciliste visokoUciliste);

  void azuriraj(VisokoUciliste visokoUciliste);

  void izbrisi(Integer visokoUcilisteId);
}
