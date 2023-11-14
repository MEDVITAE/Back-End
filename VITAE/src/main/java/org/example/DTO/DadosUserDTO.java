package org.example.DTO;

public class DadosUserDTO {
    private Double quantidade;
    private String tipo;
    private String nome;
    private String cpf;
    private int numero;
    private String sexo;
    private String dataNascimento;
    private String peso;
    private String altura;

    private String email;

    public DadosUserDTO(Double qntDoacao, String tipo, String nome, String cpf, int numero, String sexo, String dataNascimento, String peso, String altura,  String email) {
        this.quantidade = qntDoacao;
        this.tipo = tipo;
        this.nome = nome;
        this.cpf = cpf;
        this.numero = numero;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;

        this.email = email;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    @Override
    public String toString() {
        return "DadosUserDTO{" +
                "qntDoacao=" + quantidade +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", numero=" + numero +
                ", sexo='" + sexo + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", peso='" + peso + '\'' +
                ", altura='" + altura + '\'' +

                ", email='" + email + '\'' +
                '}';
    }
}
