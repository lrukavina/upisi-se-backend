package hr.lrukavina.upisisebackend.model.visokouciliste.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpremiVisokoUcilisteRequest extends AzurVisokoUcilisteRequest {}
