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
    private Long fkHospital;
    @ManyToOne
    @JoinColumn(name = "fkHospitals", referencedColumnName = "idHospital",insertable = false, updatable = false)
    private Hospital hospital;


    @ManyToOne
    @JoinColumn(name = "fkUsuario", referencedColumnName = "idUsuario",insertable = false, updatable = false)
    private Usuario usuario;


    public Doacao(RecordDoacao dados) {
        this.quantidade = dados.quantidade();
        this.tipo = dados.tipo();
        this.fkHospital = dados.fkHospital();

    }
    public void atualizaDoacao(AtualizaDoacao dados){
        this.quantidade = dados.quantidade();
        this.tipo = dados.tipo();
        this.fkHospital = dados.fkHospital();

    }
}
