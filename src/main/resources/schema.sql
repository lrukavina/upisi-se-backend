create table if not exists korisnik(
    id identity,
    ime varchar(255) not null,
    prezime varchar(255) not null,
    jmbag varchar(10),
    adresa varchar(255),
    email varchar(255) not null,
    korisnicko_ime varchar(255) not null,
    lozinka varchar(255) not null,
    rola varchar(255) not null,
    visoko_uciliste_id bigint
);

create table if not exists visoko_uciliste(
    id identity,
    naziv varchar(255) not null,
    adresa varchar(255) not null,
    postanski_broj varchar(255) not null,
    mjesto varchar(255) not null,
    iban varchar(21) not null,
    oib varchar(11) not null
);

create table if not exists studij(
    id identity,
    naziv_studija varchar(255) not null,
    naziv_smjera varchar(255) not null,
    ects_cijena int not null,
    visoko_uciliste_id bigint not null,
    constraint fk_visoko_uciliste foreign key (visoko_uciliste_id) references visoko_uciliste(id)
);

create table if not exists kolegij(
    id identity,
    naziv varchar(255) not null,
    ects int not null,
    semestar int not null,
    isvu_sifra varchar(255) not null,
    obavezan int not null,
    studij_id bigint not null,
    constraint fk_studij foreign key (studij_id) references studij(id)
);

create table if not exists kolegij_info(
    id identity,
    informacije text not null,
    kolegij_id bigint not null,
    constraint fk_kolegij_info foreign key (kolegij_id) references kolegij(id)
);

create table if not exists kolegij_nastavnik(
    id identity,
    ime varchar(255) not null,
    prezime varchar(255) not null,
    titula varchar(255) not null,
    kolegij_id bigint not null,
    constraint fk_kolegij_nastavnik foreign key (kolegij_id) references kolegij(id)
);

create table if not exists upis(
    id identity,
    semestar int not null,
    tstamp_od timestamp not null,
    tstamp_do timestamp not null,
    studij_id bigint not null,
    constraint fk_studij_upis foreign key (studij_id) references studij(id)
);

create table if not exists upis_info(
    id identity,
    poziv_na_broj varchar(255) not null,
    broj_ectsa int not null,
    upisni_broj varchar(255) not null,
    opis varchar(255) not null,
    status varchar(255) not null,
    upis_id bigint not null,
    korisnik_id bigint not null,
    constraint fk_upis foreign key (upis_id) references upis(id),
    constraint fk_korisnik foreign key (korisnik_id) references korisnik(id)
);

create table if not exists upis_info_kolegij(
    upis_info_id bigint not null,
    kolegij_id bigint not null,
    constraint fk_upis_info foreign key (upis_info_id) references upis_info(id),
    constraint fk_kolegij foreign key (kolegij_id) references kolegij(id)
);