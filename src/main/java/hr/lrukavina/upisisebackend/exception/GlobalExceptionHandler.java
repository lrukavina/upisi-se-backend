package hr.lrukavina.upisisebackend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(value = {UpisiSeException.class})
  protected ResponseEntity<Object> handleUpisiSeException(UpisiSeException ex, WebRequest request) {
    List<Poruka> poruke = ex.getPoruke();
    return handleExceptionInternal(ex, poruke, new HttpHeaders(), ex.getStatus(), request);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(
      value = {InternalAuthenticationServiceException.class})
  protected ResponseEntity<Object> handleInternalAuthenticationServiceException(
      InternalAuthenticationServiceException ex, WebRequest request) {
    Poruka poruka = Poruka.buildPoruka(VrstaPoruke.KORISNIK_NE_POSTOJI);
    return handleExceptionInternal(ex, poruka, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }
}
