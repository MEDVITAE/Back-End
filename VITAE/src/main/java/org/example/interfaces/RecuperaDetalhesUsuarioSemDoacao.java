package org.example.interfaces;

import java.time.LocalDate;

public interface RecuperaDetalhesUsuarioSemDoacao {
    String getAltura();
    LocalDate getNascimento();
    String getPeso();
    String getSexo();

    String getCpf();
    String getEmail();
    String getNome();

    String getCep();
    int getNumeroCasa();
}
