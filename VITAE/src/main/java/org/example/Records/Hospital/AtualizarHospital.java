package org.example.Records.Hospital;

import org.example.Domain.Hospital;

public record AtualizarHospital(
                                 String nome,
                                 String email,
                                 String senha,
                                 String cnpj) {
    public AtualizarHospital(Hospital hospital) {
            this(hospital.getNome(), hospital.getEmail(), hospital.getSenha(), hospital.getCnpj());
    }
}
