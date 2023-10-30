package hr.lrukavina.upisisebackend.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class UpisiSeException extends RuntimeException {

  private List<Poruka> poruke;
  private HttpStatus status;

  public UpisiSeException(List<Poruka> poruke, HttpStatus status) {
    this.poruke = poruke;
    this.status = status;
  }

  public List<Poruka> getPoruke() {
    return poruke;
  }

  public void setPoruke(List<Poruka> poruke) {
    this.poruke = poruke;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }
}
