package org.example.interfaces;

import org.example.DTO.AgendamentoDTO;
import org.example.Domain.Agenda;
import org.example.Domain.Hospital;
import org.example.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface AgendaRepository extends JpaRepository<Agenda,Long> {

    @Query(value = "SELECT u.id_usuario, u.nome AS nome,u.cpf as cpf, a.Horario AS horario FROM usuario u JOIN " +
            "Agenda a ON u.id_Usuario = a.fk_Usuario",nativeQuery = true)
    List<RecuperarValoresAgendamento> buscaDoadorAgendado();
    @Query(value = "SELECT email,cpf,nome FROM usuario where id_usuario =:id",nativeQuery = true)
    RecuperaValoresUsuario recupera(Long id);
    @Query(value = "SELECT a FROM Agenda a where a.fkHospital =:hospitalId and  a.Horario = :horario ")
    Optional<Agenda> findByHorarioExiste (int hospitalId, LocalDateTime horario);

    @Query(value = "select count(*) as doacao,sum(quantidade) as quantidade from agenda join doacao on fk_agenda = id_agenda where agenda.fk_usuario = :id",nativeQuery = true)
    RecuperaValoresAgendamentosDoacoes quantidadeDoacao(Long id);
    @Query(value ="select  hospital.nome,endereco.logradouro as rua from hospital join agenda on id_hospital = agenda.fk_hospital join endereco on endereco.fk_hospital = id_hospital where agenda.fk_usuario = :id" ,nativeQuery = true)
    List<RecuperaNomeHospital> hospital(Long id);
    @Query(value ="select  agenda.*  from hospital join agenda on id_hospital = fk_hospital where fk_usuario = :id" ,nativeQuery = true)
    List<Agenda> agenda(Long id);

    @Query(value = "select agenda.* from agenda join hospital on id_hospital = fk_  Hospital where fk_Hospital = :id ",nativeQuery = true)
    List<Agenda> listaAgendamentos(int id);
}
