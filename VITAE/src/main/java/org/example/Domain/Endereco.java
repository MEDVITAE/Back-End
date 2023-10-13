package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.Records.Endereco.AtualizaEndereco;
import org.example.Records.Endereco.RecordEndereco;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Endereco")
@Entity(name = "Endereco")

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")

public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cidade;
    private String bairro;
    private String cep;
    private String logradouro;
    private String rua;
    private int numero;
    private Long fkHospital;
    private Long fkUsuario;

    @ManyToOne
    @JoinColumn(name = "fkUsuarios", referencedColumnName = "idUsuario",insertable = false, updatable = false)
    private Usuario usuarios;
    @ManyToOne
    @JoinColumn(name = "fkHospitals", referencedColumnName = "idHospital",insertable = false, updatable = false)
    private Hospital hospitals;


    public Endereco(RecordEndereco atualizar) {

        this.cidade = atualizar.cidade();
        this.bairro = atualizar.bairro();
        this.cep = atualizar.cep();
        this.logradouro = atualizar.logradouro();
        this.rua = atualizar.rua();
        this.numero = atualizar.numero();
        this.fkUsuario = atualizar.fkUsuario();
        this.fkHospital = atualizar.fkHospital();

    }
    public void AtualizaEndereco(AtualizaEndereco atualizar){
        this.cidade = atualizar.cidade();
        this.bairro = atualizar.bairro();
        this.cep = atualizar.cep();
        this.logradouro = atualizar.logradouro();
        this.rua = atualizar.rua();
        this.numero = atualizar.numero();
        this.fkUsuario = atualizar.fkUsuario();
        this.fkHospital = atualizar.fkHospital();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Long getFkHospital() {
        return fkHospital;
    }

    public void setFkHospital(Long fkHospital) {
        this.fkHospital = fkHospital;
    }

    public Long getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Long fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
