package org.example.DTO;

public class RecuperaValoresConfirmaDoacaoDTO {
    private long idAgenda;

    private String tipo;
    private String nome;
    private String cpf;

    private String sexo;
    private String nascimento;

    private String email;

    private String cep;
    private int numeroCasa;

    public RecuperaValoresConfirmaDoacaoDTO(long idAgenda, String tipo, String nome, String cpf, String sexo, String nascimento, String email, String cep, int numeroCasa) {
        this.idAgenda = idAgenda;
        this.tipo = tipo;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.email = email;
        this.cep = cep;
        this.numeroCasa = numeroCasa;
    }

    public long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(long idAgenda) {
        this.idAgenda = idAgenda;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    @Override
    public String toString() {
        return "RecuperaValoresConfirmaDoacaoDTO{" +
                "idAgenda=" + idAgenda +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", sexo='" + sexo + '\'' +
                ", nascimento='" + nascimento + '\'' +
                ", email='" + email + '\'' +
                ", cep='" + cep + '\'' +
                ", numeroCasa=" + numeroCasa +
                '}';
    }
}
