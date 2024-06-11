package org.example.interfaces;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.example.DTO.UsuarioPorTipoSangue;
import org.example.Domain.Enfermeira;
import org.example.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String Email);

    @Query(value = "select * from usuario",nativeQuery = true)
    List<RecuperaValoresUsuario> AllUser();

    @Query(value = "select horario  from agenda join doacao on fk_agenda = id_agenda join usuario on id_usuario = agenda.fk_usuario where nome =(:nome)", nativeQuery = true)
    List<Usuario> findByName(String nome);

    @Query(value = "select c.*,usuario.*,doacao.*,endereco.cep,endereco.numero as numeroCasa from endereco join usuario on endereco.fk_usuario = id_usuario  join caracteristicas as c on c.fk_usuario = id_usuario join agenda on agenda.fk_usuario = id_usuario join doacao on id_agenda = fk_agenda where id_usuario =?1 limit 1", nativeQuery = true)
    RecuperaDetalhesUsuario findByDetalhesUser(Integer id);

    @Query(value = "select c.*,usuario.*,endereco.cep,endereco.numero as numeroCasa from endereco join usuario on endereco.fk_usuario = id_usuario  join caracteristicas as c on c.fk_usuario = id_usuario where id_usuario =?1 limit 1;", nativeQuery = true)
    RecuperaDetalhesUsuarioSemDoacao findByDetalhesUserSemDoacao(Integer id);

    @Query(value = "select count(id_usuario) as quantidade_doacao from usuario join agenda on fk_usuario = id_usuario join doacao on id_agenda = fk_agenda where id_usuario = ?1", nativeQuery = true)
    int quantidadeDoacao(Integer id);

    @Transactional
    @Modifying
    @Query(value = "update usuario set email = :email where id_usuario = :id", nativeQuery = true)
    void AtualizaEmail(String email, Long id);

    @Query(value = "select c.*,usuario.*,endereco.cep,endereco.numero as numeroCasa, a.id_agenda as idAgenda from endereco join usuario on endereco.fk_usuario = id_usuario  join caracteristicas as c on c.fk_usuario = id_usuario join agenda as a on usuario.id_usuario = a.fk_usuario  where cpf = ?1 limit 1;", nativeQuery = true)
    RecuperaDetalhesUsuarioDoaco findByCpf(String cpf);

    @Query(value = "select  usuario.nome from usuario where id_usuario = ?1", nativeQuery = true)
    String findByNome(Long id);

    @Query(value = "select usuario.*,h.nome as nomeHospital,h.senha as senhaHospital,h.email as emailHospital,h.cnpj   from usuario join hospital h on h.id_hospital = fk_hospital where role = 'ENFERMEIRA' or 'RECEPCAO';", nativeQuery = true)
    List<RecuperaValoresFuncionarioHospital> findByFuncionario();

    @Query(value = "select distinct usuario.nome,usuario.email from usuario join agenda on usuario.id_usuario = agenda.fk_usuario JOIN doacao ON agenda.id_agenda = doacao.fk_agenda where tipo = :tipoSangue",nativeQuery = true)
    List<RecuperaValoresUsuario> findByUsuarioPorTipoSangue(String tipoSangue);
}
