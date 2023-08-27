package org.example.Domain;

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
@EqualsAndHashCode(of= "id")

public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantidade;
    private String tipo;
    private Long fkAgenda;


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
}
