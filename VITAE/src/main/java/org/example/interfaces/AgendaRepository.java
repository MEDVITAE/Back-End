package org.example.interfaces;

import org.example.DTO.AgendamentoDTO;
import org.example.Domain.Agenda;
import org.example.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AgendaRepository extends JpaRepository<Agenda,Long> {

    @Query(value = "SELECT u.id_usuario, u.nome AS nome,u.cpf as cpf, a.Horario AS horario FROM usuario u JOIN " +
            "Agenda a ON u.id_Usuario = a.fk_Usuario",nativeQuery = true)
    List<RecuperarValoresAgendamento> buscaDoadorAgendado();
    @Query(value = "SELECT email,cpf,nome FROM usuario where id_usuario =:id",nativeQuery = true)
    RecuperaValoresUsuario recupera(Long id);
}
