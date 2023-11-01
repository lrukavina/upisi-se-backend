package hr.lrukavina.upisisebackend.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

public class Utils {
  private Utils() {}

  public static String[] ignoreNullFieldove(Object source) {
    Set<String> nullFieldovi = dohvatiNullFieldove(source);
    return nullFieldovi.toArray(new String[0]);
  }

  private static Set<String> dohvatiNullFieldove(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
    Set<String> nullFieldovi = new HashSet<>();

    for (java.beans.PropertyDescriptor pd : pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null) {
        nullFieldovi.add(pd.getName());
      }
    }
    return nullFieldovi;
  }

  public static Integer desifrirajId(String sifra) {
    if (sifra == null) {
      return null;
    }
    return Integer.valueOf(sifra);
  }

  public static String sifrirajId(Integer id) {
    if (id == null) {
      return null;
    }
    return String.format("%03d", id);
  }
}
