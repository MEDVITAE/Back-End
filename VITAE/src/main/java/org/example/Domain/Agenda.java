package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.Records.Agenda.AtualizaAgenda;
import org.example.Records.Agenda.RecordAgenda;
import org.example.Records.Doacao.RecordDoacao;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "Agenda")
@Entity(name = "Agenda")

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Agenda {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    private LocalDateTime Horario;
    @Getter
    @Setter


    private int fkUsuario;
    @Getter
    @Setter
    private int fkHospital;

    @ManyToOne
    @JoinColumn(name = "fkUsuarios", referencedColumnName = "idUsuario",insertable = false, updatable = false)
    private Usuario usuarios;
    @ManyToOne
    @JoinColumn(name = "fkHospitals", referencedColumnName = "idHospital",insertable = false, updatable = false)
    private Hospital hospitals;

    public Agenda(RecordAgenda dados) {

        this.Horario = dados.Horario();
        this.fkUsuario = dados.fkUsuario();
        this.fkHospital = dados.fkHospital();
    }



    public  void atualizaAgenda(AtualizaAgenda dados){
        this.Horario = dados.Horario();
        this.fkUsuario = dados.fkUsuario();
        this.fkHospital = dados.fkHospital();
    }
}
