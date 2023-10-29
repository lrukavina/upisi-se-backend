create table if not exists korisnik(
    id identity,
    ime varchar(255) not null,
    prezime varchar(255) not null,
    email varchar(255) not null,
    korisnicko_ime varchar(255) not null,
    lozinka varchar(255) not null,
    rola varchar(255) not null
);