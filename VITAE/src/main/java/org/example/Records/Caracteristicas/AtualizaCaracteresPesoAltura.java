package org.example.Records.Caracteristicas;

import org.example.Domain.Caracteristicas;
import org.example.Domain.Endereco;

public record AtualizaCaracteresPesoAltura(
        String altura,

        String peso
) {
    public AtualizaCaracteresPesoAltura(Caracteristicas caracteristicas){
        this(
                caracteristicas.getAltura(),
                caracteristicas.getPeso());
    }
}
