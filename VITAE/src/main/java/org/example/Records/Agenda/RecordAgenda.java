package org.example.Records.Agenda;

import java.time.LocalDateTime;

public record RecordAgenda(
        LocalDateTime Horario,
        int fkUsuario,
        int fkHospital
) {
}
