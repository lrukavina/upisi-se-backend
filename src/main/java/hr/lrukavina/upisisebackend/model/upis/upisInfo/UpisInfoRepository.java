package hr.lrukavina.upisisebackend.model.upis.upisInfo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UpisInfoRepository {
  UpisInfo dohvati(Integer upisInfoId);

  void spremi(UpisInfo upisInfo);

  void azuriraj(UpisInfo upisInfo);

  void izbrisi(Integer upisInfoId);
}
