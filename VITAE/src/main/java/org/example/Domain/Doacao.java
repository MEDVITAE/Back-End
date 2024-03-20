package org.example.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.Records.Doacao.AtualizaDoacao;
import org.example.Records.Doacao.RecordDoacao;

@Table(name = "Doacao")
@Entity(name = "Doacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "idDoacao")

public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoacao;
    private Double quantidade;
    private String tipo;
    private Long fkAgenda;

    @OneToOne
    @JoinColumn(name = "fkAgenda", referencedColumnName = "idAgenda",insertable = false, updatable = false)
    private Agenda Agenda;


    public Doacao(RecordDoacao dados) {
        this.quantidade = dados.quantidade();
        this.tipo = dados.tipo();
        this.fkAgenda = dados.fkAgenda();

    }
    public void atualizaDoacao(AtualizaDoacao dados){
        this.quantidade = dados.quantidade();
        this.tipo = dados.tipo();
        this.fkAgenda = dados.fkAgenda();

    }

    public Long getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(Long idDoacao) {
        this.idDoacao = idDoacao;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getFkAgenda() {
        return fkAgenda;
    }

    public void setFkAgenda(Long fkAgenda) {
        this.fkAgenda = fkAgenda;
    }

    public org.example.Domain.Agenda getAgenda() {
        return Agenda;
    }

    public void setAgenda(org.example.Domain.Agenda agenda) {
        Agenda = agenda;
    }
}
