package org.example.Records.Endereco;

import org.example.Domain.Endereco;

public record AtualizaEnderecoNumeroCep(
        String cep,

        int numero

) {
    public AtualizaEnderecoNumeroCep(Endereco endereco){
        this(endereco.getCep(),
           endereco.getNumero());
    }


}
