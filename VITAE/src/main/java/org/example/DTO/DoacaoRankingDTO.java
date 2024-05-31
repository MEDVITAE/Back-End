package org.example.DTO;

public class DoacaoRankingDTO {
    String nome;
    Double totalDoado;

    public DoacaoRankingDTO(String nome, Double totalDoado) {
        this.nome = nome;
        this.totalDoado = totalDoado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getTotalDoado() {
        return totalDoado;
    }

    public void setTotalDoado(Double totalDoado) {
        this.totalDoado = totalDoado;
    }

    @Override
    public String toString() {
        return "DoacaoRankingDTO{" +
                "nome='" + nome + '\'' +
                ", totalDoado=" + totalDoado +
                '}';
    }
}
