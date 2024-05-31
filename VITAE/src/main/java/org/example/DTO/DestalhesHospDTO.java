package org.example.DTO;

public class DestalhesHospDTO {
    private String nome;
    private String cep;
    private String rua;
    private int numero;
    private String bairro;

    public DestalhesHospDTO(String nome, String cep, String rua, int numero, String bairro) {
        this.nome = nome;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return "DestalhesHospDTO{" +
                "nome='" + nome + '\'' +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", numero=" + numero +
                ", bairro='" + bairro + '\'' +
                '}';
    }
}
