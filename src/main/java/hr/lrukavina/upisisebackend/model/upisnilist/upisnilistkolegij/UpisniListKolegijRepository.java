package hr.lrukavina.upisisebackend.model.upisnilist.upisnilistkolegij;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UpisniListKolegijRepository {
  void spremi(@Param("upisniListId") Integer upisniListId, @Param("kolegijId") Integer kolegijId);

  void izbrisi(@Param("upisniListId") Integer upisniListId);
}
