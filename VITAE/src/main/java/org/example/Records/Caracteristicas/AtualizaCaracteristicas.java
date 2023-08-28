package org.example.Records.Caracteristicas;

import org.example.Domain.Caracteristicas;

import java.util.Date;

public record AtualizaCaracteristicas (

        String peso,
        String altura,
        boolean tatto,
        String sexo,
        Date dtNascimento,
        Long fkUsuario
) {
    public AtualizaCaracteristicas(Caracteristicas caracteristicas) {this(caracteristicas.getPeso(), caracteristicas.getAltura(), caracteristicas.isTatto(), caracteristicas.getSexo(), caracteristicas.getDtNascimento(), caracteristicas.getFkUsuario());}
}
