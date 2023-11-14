package org.example.interfaces;

import java.time.LocalDateTime;

public interface RecuperarValoresAgendamento {
    Long getId_usuario();

    String getNome();
    LocalDateTime getHorario();

    String getCpf();

}
