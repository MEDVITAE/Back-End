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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime Horario;
    private int fkUsuario;
    private int fkHospital;

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
