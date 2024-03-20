package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.Records.Hospital.AtualizarHospital;
import org.example.Records.Hospital.RecordHospital;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Hospital")
@Entity(name = "Hospital")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHospital;
    private String nome;
    private String email;
    private String senha;
    private String cnpj;
    @OneToMany(mappedBy = "fkHospital")
    private List<Endereco> enderecos = new ArrayList<>();
    @OneToMany(mappedBy = "fkHospital")
    private List<Agenda> agendamentos = new ArrayList<>();
    @OneToMany(mappedBy = "FkHospital", cascade = CascadeType.ALL)
    private List<Usuario> usuarios = new ArrayList<>();


    public Hospital(RecordHospital cadastroHospital) {
        this.nome = cadastroHospital.nome();
        this.email = cadastroHospital.email();
        this.senha = cadastroHospital.senha();
        this.cnpj = cadastroHospital.cnpj();
    }

    public void AtualizaHospital(AtualizarHospital atualizarDados) {
        this.nome = atualizarDados.nome();
        this.email = atualizarDados.email();
        this.senha = atualizarDados.senha();
        this.cnpj = atualizarDados.cnpj();
    }

    public Long getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(Long idHospital) {
        this.idHospital = idHospital;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Agenda> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agenda> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
