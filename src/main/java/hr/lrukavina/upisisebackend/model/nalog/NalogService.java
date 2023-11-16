package hr.lrukavina.upisisebackend.model.nalog;

import hr.lrukavina.upisisebackend.model.upisnilist.UpisniList;

import java.io.IOException;

public interface NalogService {

  Nalog generirajZaUpisniList(UpisniList upisniList);

  PutanjaDatoteke generirajHub3(Nalog nalog, String upisniBroj) throws IOException;
}
