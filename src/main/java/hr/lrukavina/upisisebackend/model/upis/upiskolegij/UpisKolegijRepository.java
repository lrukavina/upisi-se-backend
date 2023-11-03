package hr.lrukavina.upisisebackend.model.upis.upiskolegij;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UpisKolegijRepository {
  void spremi(@Param("upisId") Integer upisId, @Param("kolegijId") Integer kolegijId);

  void izbrisi(@Param("upisId") Integer upisId);
}
