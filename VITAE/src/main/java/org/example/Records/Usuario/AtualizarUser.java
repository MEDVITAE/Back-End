package org.example.Records.Usuario;

import org.example.Domain.Enfermeira;
import org.example.Domain.Paciente;
import org.example.Domain.Recepcao;
import org.example.Domain.Usuario;
import org.example.Enums.Usuarios.UserRole;

public record AtualizarUser (
        String nome,
        String email,
        String senha,
        UserRole role

)
{
    public AtualizarUser(Usuario usuario,Paciente paciente){
        this(
                paciente.getNome(), usuario.getEmail(), usuario.getSenha(),usuario.getRole()
        );
    }
    public AtualizarUser(Usuario usuario, Enfermeira emfermeira){
        this(
                emfermeira.getNome(),usuario.getEmail(),usuario.getSenha(),usuario.getRole()
        );
    }
    public AtualizarUser(Usuario usuario,Recepcao recepcao){
        this(
                recepcao.getNome(), usuario.getEmail(), usuario.getSenha(),usuario.getRole()
        );
    }



}
