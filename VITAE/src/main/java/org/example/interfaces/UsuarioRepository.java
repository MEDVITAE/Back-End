package org.example.interfaces;

import org.example.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
   UserDetails findByEmail(String Email);
   @Query(value = "select horario  from agenda join doacao on fk_agenda = id_agenda join usuario on id_usuario = agenda.fk_usuario where nome =(:nome)",nativeQuery = true)
   List<Usuario> findByName(String nome);
}
