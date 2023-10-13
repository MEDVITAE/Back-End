package org.example.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.Records.Agenda.AtualizaAgenda;
import org.example.Records.Agenda.RecordAgenda;
import org.example.Records.Doacao.RecordDoacao;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "Agenda")
@Entity(name = "Agenda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "idAgenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgenda;

    private LocalDateTime Horario;

    private int fkUsuario;

    private int fkHospital;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fkUsuarios", referencedColumnName = "idUsuario",insertable = false, updatable = false)
    private Usuario usuarios;
    @ManyToOne
    @JsonIgnore
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

    public LocalDateTime getHorario() {
        return Horario;
    }

    public void setHorario(LocalDateTime horario) {
        Horario = horario;
    }
}
