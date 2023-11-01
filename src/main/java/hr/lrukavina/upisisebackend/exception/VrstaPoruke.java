package hr.lrukavina.upisisebackend.exception;

public enum VrstaPoruke {
  KORISNIK_NE_POSTOJI("01", "Pogrešno korisničko ime ili lozinka"),
  KORISNIK_NE_POSTOJI_U_BAZI("02", "Korisnik ne postoji u bazi podataka"),
  VISOKO_UCILISTE_NE_POSTOJI_U_BAZI("03", "Visoko učilište ne postoji u bazi podataka"),
  STUDIJ_NE_POSTOJI_U_BAZI("04", "Studij ne postoji u bazi podataka");

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
