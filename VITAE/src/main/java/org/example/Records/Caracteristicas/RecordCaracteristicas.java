package org.example.Records.Caracteristicas;

import java.util.Date;

public record RecordCaracteristicas (
    String peso,
    String altura,
    boolean tatto,
    String sexo,
    Date dtNascimento,
    Long fkUsuario

) {
}
