package org.example.Records.Endereco;

import org.example.Domain.Endereco;
import org.example.Domain.Hospital;
import org.example.Domain.Usuario;

public record AtualizaEndereco(String cidade,
                               String bairro,
                               String cep,
                               String logradouro,
                               String rua,
                               int numero,
                               Long fkUsuario,
                               Long fkHospital) {
public AtualizaEndereco(Endereco endereco){
    this(endereco.getCidade(), endereco.getBairro(),endereco.getCep(),endereco.getLogradouro(),
            endereco.getRua(),endereco.getNumero(),endereco.getFkUsuario(), endereco.getFkHospital());
    }
}
