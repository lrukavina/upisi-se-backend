package hr.lrukavina.upisisebackend.model.korisnik;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Korisnik implements UserDetails {
  private Integer id;
  private String ime;
  private String prezime;
  private String jmbag;
  private Integer semestar;
  private String adresa;
  private String email;
  private String korisnickoIme;
  private String lozinka;
  private Rola rola;
  private Integer visokoUcilisteId;
  private Integer studijId;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (rola == null) {
      return null;
    }
    return List.of(new SimpleGrantedAuthority(rola.name()));
  }

  @Override
  public String getPassword() {
    return lozinka;
  }

  @Override
  public String getUsername() {
    return korisnickoIme;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
