package org.example.Records.Caracteristicas;

import java.time.LocalDate;
import java.util.Date;

public record RecordCaracteristicas (
    String peso,
    String altura,
    boolean tatto,
    String sexo,
    LocalDate nascimento,
     boolean apto,
    Long fkUsuario

) {
}
