package org.example.Records.Doacao;

import org.example.Domain.Doacao;

public record AtualizaDoacao(
        Double quantidade,
        String tipo,
        Long fkAgenda
      ) {
    public AtualizaDoacao(Doacao doacao){
       this(doacao.getQuantidade(),doacao.getTipo(),doacao.getFkAgenda());
    }
}
