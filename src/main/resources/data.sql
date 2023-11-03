insert into korisnik(ime, prezime, jmbag, semestar, adresa, email, korisnicko_ime, lozinka, rola, visoko_uciliste_id, studij_id) values
('Luka', 'Rukavina', null, null, null, 'lrukavina@tvz.hr', 'lrukavina', '$2a$10$AmPJwsPTr14JM16zGoxeEOBEYPWcaFA.liTXvR5jionq4YUP4/rru', 'ADMIN', null, null),
('Pero', 'Perić', '1234567890', 3, 'Vukovarska 25', 'pperic@tvz.hr', 'pperic', '$2a$10$AmPJwsPTr14JM16zGoxeEOBEYPWcaFA.liTXvR5jionq4YUP4/rru', 'STUDENT', 1, 1);

insert into visoko_uciliste(naziv, adresa, postanski_broj, mjesto, iban, oib) values
('Tehničko veleučilište u Zagrebu', 'Vrbik 8', '10000', 'Zagreb', 'HR1723600001101234565', '08814003451'),
('Fakultet političkih znanosti', 'Lepušićeva 6', '10000', 'Zagreb', 'HR1723600001101234565', '08814003451'),
('Fakultet prometnih znanosti', 'Vukelićeva 4', '10000', 'Zagreb', 'HR1723600001101234565', '08814003451'),
('Filozofski fakultet', 'Ivana Lučića 3', '10000', 'Zagreb', 'HR1723600001101234565', '08814003451'),
('Stomatološki fakultet', 'Gundulićeva 5', '10000', 'Zagreb', 'HR1723600001101234565', '08814003451'),
('Medicinski fakultet', 'Šalata 3b', '10000', 'Zagreb', 'HR1723600001101234565', '08814003451'),
('Geodetski fakultet', 'Kačićeva 26', '10000', 'Zagreb', 'HR1723600001101234565', '08814003451'),
('Kineziološki fakultet', 'Horvaćanski zavoj 15', '10000', 'Zagreb', 'HR1723600001101234565', '08814003451'),
('Muzička akademija', 'Trg Republike Hrvatske 12', '10000', 'Zagreb', 'HR1723600001101234565', '08814003451'),
('Prirodoslovno-matematički fakultet-matematički odsjek', 'Bijenička cesta 30', '10000', 'Zagreb', 'HR1723600001101234565', '08814003451');

insert into studij(naziv_studija, naziv_smjera, ects_cijena, visoko_uciliste_id) values
('Stručni prijediplomski studij Računarstvo', 'Programsko inženjerstvo', 33.33, 1),
('Stručni prijediplomski studij Računarstvo', 'Inženjerstvo računalnih sustava i mreža', 33, 1),
('Stručni diplomski studij Digitalna ekonomija', 'Digitalna ekonomija', 33, 1),
('Stručni prijediplomski studij Graditeljstvo', 'Građevinsko poduzetništvo', 33, 1),
('Stručni prijediplomski studij Graditeljstvo', 'Niskogradnja', 33, 1),
('Stručni prijediplomski studij Mehatronika', 'Mehatronika', 33, 1),
('Stručni prijediplomski studij Strojarstvo', 'Strojarstvo', 33, 1),
('Stručni prijediplomski studij Informatika', 'Organizacija i informatizacija ureda', 33, 1),
('Stručni prijediplomski studij Informatika', 'Elektroničko poslovanje', 33, 1),
('Stručni prijediplomski studij Informatika', 'Informatički dizajn', 33, 1);

insert into kolegij(naziv, ects, semestar, isvu_sifra, obavezan, studij_id) values
('NOSQL i napredna big data analitika', 6, 3, '130969', 1, 1),
('Alati i postupci umjetne inteligencije', 6, 3, '130970', 1, 1),
('Reinžinjering poslovnih procesa', 6, 3, '130971', 1, 1),
('Projektiranje inovacija', 6, 3, '130972', 0, 1),
('Napredne tehnike projektiranja web servisa', 6, 3, '130973', 0, 1),
('Razvoj mobilnih aplikacija', 6, 3, '130974', 0, 1),
('Modeliranje i administracija baza podataka', 6, 3, '130974', 0, 1),
('Digitalna ekonomija', 5, 1, '130975', 1, 1),
('Diskretna matematika', 6, 2, '130976', 0, 1),
('Metodologija stručnog i istraživačkog rada', 6, 4, '130977', 1, 1);

insert into kolegij_info(informacije, kolegij_id) values
('Informacije o kolegiju nisu dostupne.', 1),
('Informacije o kolegiju nisu dostupne.', 2),
('Informacije o kolegiju nisu dostupne.', 3),
('Informacije o kolegiju nisu dostupne.', 4),
('Informacije o kolegiju nisu dostupne.', 5),
('Informacije o kolegiju nisu dostupne.', 6),
('Informacije o kolegiju nisu dostupne.', 7),
('Informacije o kolegiju nisu dostupne.', 8),
('Informacije o kolegiju nisu dostupne.', 9),
('Informacije o kolegiju nisu dostupne.', 10);

insert into kolegij_nastavnik(ime, prezime, titula, kolegij_id) values
('John', 'Doe', 'dr.sc.', 1),
('John', 'Doe', 'dr.sc.', 2),
('John', 'Doe', 'dr.sc.', 3),
('John', 'Doe', 'dr.sc.', 4),
('John', 'Doe', 'dr.sc.', 5),
('John', 'Doe', 'dr.sc.', 6),
('John', 'Doe', 'dr.sc.', 7),
('John', 'Doe', 'dr.sc.', 8),
('John', 'Doe', 'dr.sc.', 9),
('John', 'Doe', 'dr.sc.', 10);

insert into upis(semestar, tstamp_od, tstamp_do, min_broj_ectsa, max_broj_ectsa, studij_id) values
(3, '2023-09-01 00:00:00', '2023-09-30 00:00:00', 15, 30, 1),
(3, '2023-09-01 00:00:00', '2023-09-30 00:00:00', 15, 30, 2),
(3, '2023-09-01 00:00:00', '2023-09-30 00:00:00', 15, 30, 3),
(3, '2023-09-01 00:00:00', '2023-09-30 00:00:00', 15, 30, 4),
(3, '2023-09-01 00:00:00', '2023-09-30 00:00:00', 15, 30, 5),
(3, '2023-09-01 00:00:00', '2023-09-30 00:00:00', 15, 30, 6),
(3, '2023-09-01 00:00:00', '2023-09-30 00:00:00', 15, 30, 7),
(3, '2023-09-01 00:00:00', '2023-09-30 00:00:00', 15, 30, 8),
(3, '2023-09-01 00:00:00', '2023-09-30 00:00:00', 15, 30 ,9),
(3, '2023-09-01 00:00:00', '2023-09-30 00:00:00', 15, 30 ,10);

insert into upis_kolegij(upis_id, kolegij_id) values
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5);

insert into upisni_list(broj_ectsa, upisni_broj, cijena_ects, ukupna_cijena, status, upis_id, korisnik_id) values
(30, 'UB0001', 33.33, 1000, 'U_TIJEKU', 1, 1);

insert into upisni_list_kolegij(upisni_list_id, kolegij_id) values
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5);

