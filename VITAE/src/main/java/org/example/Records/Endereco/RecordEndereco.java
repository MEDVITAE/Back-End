package org.example.Records.Endereco;

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
