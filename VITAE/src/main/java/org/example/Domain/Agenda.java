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

    public Long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public int getFkHospital() {
        return fkHospital;
    }

    public void setFkHospital(int fkHospital) {
        this.fkHospital = fkHospital;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }

    public Hospital getHospitals() {
        return hospitals;
    }

    public void setHospitals(Hospital hospitals) {
        this.hospitals = hospitals;
    }
}
