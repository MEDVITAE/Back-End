package org.example.DTO;

import org.example.Enums.Usuarios.UserRole;

public class FuncionarioHospitalDTO {
    private String  email;
    private String  senha;
    private UserRole role;
    private String  nome;
    private Integer  fkHospital;
    private String  cpf;
    private String  emailHospital;
    private String  nomeHospital;
    private String  senhaHospital;
    private String  cnpj;

    public FuncionarioHospitalDTO(String email, String senha, UserRole role, String nome, Integer fkHospital, String cpf, String emailHospital, String nomeHospital, String senhaHospital, String cnpj) {
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.nome = nome;
        this.fkHospital = fkHospital;
        this.cpf = cpf;
        this.emailHospital = emailHospital;
        this.nomeHospital = nomeHospital;
        this.senhaHospital = senhaHospital;
        this.cnpj = cnpj;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getFkHospital() {
        return fkHospital;
    }

    public void setFkHospital(Integer fkHospital) {
        this.fkHospital = fkHospital;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmailHospital() {
        return emailHospital;
    }

    public void setEmailHospital(String emailHospital) {
        this.emailHospital = emailHospital;
    }

    public String getNomeHospital() {
        return nomeHospital;
    }

    public void setNomeHospital(String nomeHospital) {
        this.nomeHospital = nomeHospital;
    }

    public String getSenhaHospital() {
        return senhaHospital;
    }

    public void setSenhaHospital(String senhaHospital) {
        this.senhaHospital = senhaHospital;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "FuncionarioHospitalDTO{" +
                "email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", role=" + role +
                ", nome='" + nome + '\'' +
                ", fkHospital=" + fkHospital +
                ", cpf='" + cpf + '\'' +
                ", emailHospital='" + emailHospital + '\'' +
                ", nomeHospital='" + nomeHospital + '\'' +
                ", senhaHospital='" + senhaHospital + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}
