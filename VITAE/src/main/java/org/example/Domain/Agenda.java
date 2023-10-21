package org.example.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.Records.Agenda.AtualizaAgenda;
import org.example.Records.Agenda.RecordAgenda;

import java.time.LocalDateTime;

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

    @NotBlank
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

    public  void atualizaAgenda(AtualizaAgenda update){
        this.Horario = update.Horario();
        this.fkUsuario = update.fkUsuario();
        this.fkHospital = update.fkHospital();
    }

    public LocalDateTime getHorario() {
        return LocalDateTime.now();
    }

    public void setHorario(LocalDateTime horario) {
        Horario = horario;
    }
}
