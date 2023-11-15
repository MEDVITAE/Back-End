package org.example.interfaces;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.example.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
   Usuario findByEmail(String Email);
   @Query(value = "select horario  from agenda join doacao on fk_agenda = id_agenda join usuario on id_usuario = agenda.fk_usuario where nome =(:nome)",nativeQuery = true)
   List<Usuario> findByName(String nome);
    @Query(value = "select c.*,usuario.*,doacao.* from  caracteristicas c join usuario on c.fk_usuario = id_usuario join agenda on agenda.fk_usuario = id_usuario join doacao on id_agenda = fk_agenda where id_usuario =?1 limit 1",nativeQuery = true)
    RecuperaDetalhesUsuario findByDetalhesUser(Integer id);

    @Query(value = "select count(id_usuario) as quantidade_doacao from usuario join agenda on fk_usuario = id_usuario join doacao on id_agenda = fk_agenda where id_usuario = ?1",nativeQuery = true)
    int quantidadeDoacao( Integer id);
    @Transactional
    @Modifying
    @Query(value = "update usuario set email = :email where id_usuario = :id",nativeQuery = true)
    void AtualizaEmail(String email,Long id);
}
