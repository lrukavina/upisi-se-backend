package hr.lrukavina.upisisebackend.utils.barkod;

import hr.lrukavina.upisisebackend.model.nalog.Nalog;

import java.io.IOException;

public interface BarkodService {

  String generirajBarkod(Nalog nalog, String upisniBroj) throws IOException;
}
