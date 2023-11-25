package org.example.interfaces;

public interface RecuperaListaAgendamentos {
    Integer getId_Agenda();
    Integer getFk_Usuario();
    Integer getFk_Hospital();
    String getCpf();
    String getNome();
    String getHorario();
}
