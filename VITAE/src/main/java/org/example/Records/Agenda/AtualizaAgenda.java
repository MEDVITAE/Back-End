package org.example.Records.Agenda;

import org.example.Domain.Agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AtualizaAgenda(
        LocalDateTime Horario,
        int fkUsuario,
        int fkHospital
) {
    public AtualizaAgenda(Agenda agenda){
        this(
                agenda.getHorario(),agenda.getFkHospital(),agenda.getFkUsuario()
        );
    }
}
