package org.example.Records.Endereco;

import org.example.Domain.Hospital;
import org.example.Domain.Usuario;

import java.util.List;

public record RecordEndereco(
         String cidade,
         String bairro,
         String cep,
         String logradouro,
         String rua,
         int numero,
         Long fkUsuario,
         Long fkHospital
) {
}
