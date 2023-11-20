package org.example.interfaces;

import java.time.LocalDate;

public interface RecuperaDetalhesUsuarioDoaco {


    LocalDate getNascimento();

    String getSexo();

    String getCpf();

    String getEmail();

    String getNome();

    String getTipo();

    String getCep();
    int getNumeroCasa();
    long getIdAgenda();
}
