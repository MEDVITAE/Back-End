package org.example.Records.Doacao;

import java.time.LocalDate;

public record RecordDoacao(

        Double quantidade,
        String tipo,
        Long fkAgenda

      ) {
}
