package org.example.interfaces;

import java.time.LocalDate;

public interface RecuperaDetalhesUsuarioDoaco {


    LocalDate getNascimento();
    Long getFk_Usuario();

    String getSexo();

    String getCpf();

    String getEmail();

    String getNome();

    String getTipo();

    String getCep();
    int getNumeroCasa();
    long getIdAgenda();
}
