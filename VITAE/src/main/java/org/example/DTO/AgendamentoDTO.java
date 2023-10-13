package org.example.DTO;

import org.example.Domain.Agenda;
import org.example.Domain.Paciente;

import java.time.LocalDateTime;

public class AgendamentoDTO {

    private String nome;
    private LocalDateTime horario;

    public AgendamentoDTO(String nome, LocalDateTime horario) {
        this.nome = nome;
        this.horario = horario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "AgendamentoDTO{" +
                "nome='" + nome + '\'' +
                ", horario=" + horario +
                '}';
    }
}
