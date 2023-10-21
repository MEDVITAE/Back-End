package org.example.Records.Hospital;

import org.example.Domain.Agenda;
import org.example.Domain.Endereco;
import org.example.Domain.Hospital;

import java.util.List;

public record AtualizarHospital(
        Long id,
        String nome,
        String email,
        String senha,
        String cnpj,
        List<Endereco> listEndereco,
        List<Agenda> listAgenda
) {
    public AtualizarHospital(Hospital hospital) {
            this(
                    hospital.getIdHospital(),
                    hospital.getNome(),
                    hospital.getEmail(),
                    hospital.getSenha(),
                    hospital.getCnpj(),
                    hospital.getEnderecos(),
                    hospital.getAgendamentos()
            );
    }
}
