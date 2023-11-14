package org.example.Records.Caracteristicas;

import org.example.Domain.Caracteristicas;

import java.time.LocalDate;
import java.util.Date;

public record AtualizaCaracteristicas (

        String peso,
        String altura,
        boolean tatto,
        String sexo,
        LocalDate nascimento,
        boolean apto,
        Long fkUsuario
) {
    public AtualizaCaracteristicas(Caracteristicas caracteristicas) {this(caracteristicas.getPeso(), caracteristicas.getAltura(), caracteristicas.isTatto(), caracteristicas.getSexo(), caracteristicas.getNascimento(), caracteristicas.isApto(), caracteristicas.getFkUsuario());}
}
