package org.example.DTO;

import org.example.Domain.Agenda;
import org.example.Domain.Hospital;
import org.example.interfaces.RecuperaNomeHospital;

import java.util.List;

public class InfoAgendamentosHospitaisDTO {
    private Double quantidade;
    private int quantidadeDoacao;
    private List<Agenda> agenda;
    private List<RecuperaNomeHospital> hospital;

    public InfoAgendamentosHospitaisDTO(Double quantidade, int quantidadeDoacao, List<Agenda> agenda, List<RecuperaNomeHospital> hospital) {
        this.quantidade = quantidade;
        this.quantidadeDoacao = quantidadeDoacao;
        this.agenda = agenda;
        this.hospital = hospital;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeDoacao() {
        return quantidadeDoacao;
    }

    public void setQuantidadeDoacao(int quantidadeDoacao) {
        this.quantidadeDoacao = quantidadeDoacao;
    }

    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Agenda> agenda) {
        this.agenda = agenda;
    }

    public List<RecuperaNomeHospital> getHospital() {
        return hospital;
    }

    public void setHospital(List<RecuperaNomeHospital> hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "InfoAgendamentosHospitaisDTO{" +
                "quantidade=" + quantidade +
                ", quantidadeDoacao=" + quantidadeDoacao +
                ", agenda=" + agenda +
                ", hospital=" + hospital +
                '}';
    }
}
