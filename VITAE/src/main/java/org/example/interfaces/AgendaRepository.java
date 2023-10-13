package org.example.interfaces;

import org.example.DTO.AgendamentoDTO;
import org.example.Domain.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AgendaRepository extends JpaRepository<Agenda,Long> {

    @Query(value = "SELECT u.nome AS nome, a.Horario AS horario FROM usuario u JOIN " +
            "Agenda a ON u.id_Usuario = a.fk_Usuario",nativeQuery = true)
    List<RecuperarValoresAgendamento> buscaDoadorAgendado();
}
