package org.example.DTO;

import org.example.Domain.Agenda;
import org.example.Domain.Paciente;

import java.time.LocalDateTime;

public class AgendamentoDTO {

    private String nome;
    private LocalDateTime horario;
    private  String cpf;

    public AgendamentoDTO(String nome, LocalDateTime horario, String cpf) {
        this.nome = nome;
        this.horario = horario;
        this.cpf = cpf;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "AgendamentoDTO{" +
                "nome='" + nome + '\'' +
                ", horario=" + horario +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
