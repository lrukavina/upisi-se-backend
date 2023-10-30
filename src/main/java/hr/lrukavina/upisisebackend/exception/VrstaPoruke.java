package hr.lrukavina.upisisebackend.exception;

public enum VrstaPoruke {
  KORISNIK_NE_POSTOJI("01", "Pogrešno korisničko ime ili lozinka");

  private final String sifra;
  private final String poruka;

  VrstaPoruke(String sifra, String poruka) {
    this.sifra = sifra;
    this.poruka = poruka;
  }

  public String getSifra() {
    return sifra;
  }

  public String getPoruka() {
    return poruka;
  }
}
