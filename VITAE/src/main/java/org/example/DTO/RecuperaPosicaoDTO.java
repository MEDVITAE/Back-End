package org.example.DTO;

public class RecuperaPosicaoDTO {
    private int posicao;

    public RecuperaPosicaoDTO(int posicao) {
        this.posicao = posicao;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    @Override
    public String toString() {
        return "RecuperaPosicaoDTO{" +
                "posicao=" + posicao +
                '}';
    }
}
