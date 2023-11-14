package org.example.DTO;

import java.util.Date;

public class DadosUserDTO {
    private Double quantidade;
    private String tipo;
    private String nome;
    private String cpf;
    private int numero;
    private String sexo;
    private String nascimento;
    private String peso;
    private String altura;
    private String email;
    private boolean apto;

    public DadosUserDTO(Double quantidade, String tipo, String nome, String cpf, int numero, String sexo, String nascimento, String peso, String altura, String email, boolean apto) {
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.nome = nome;
        this.cpf = cpf;
        this.numero = numero;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.peso = peso;
        this.altura = altura;
        this.email = email;
        this.apto = apto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isApto() {
        return apto;
    }

    public void setApto(boolean apto) {
        this.apto = apto;
    }

    @Override
    public String toString() {
        return "DadosUserDTO{" +
                "quantidade=" + quantidade +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", numero=" + numero +
                ", sexo='" + sexo + '\'' +
                ", nascimento='" + nascimento + '\'' +
                ", peso='" + peso + '\'' +
                ", altura='" + altura + '\'' +
                ", email='" + email + '\'' +
                ", apto=" + apto +
                '}';
    }
}
