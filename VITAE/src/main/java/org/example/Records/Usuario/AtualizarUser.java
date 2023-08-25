package org.example.Records.Usuario;

import org.example.Domain.Usuario;

public record AtualizarUser (
        String nome,
        String telefone,
        String senha,
        String email

)
{
    public AtualizarUser(Usuario usuario){
        this(
                usuario.getNome(), usuario.getTelefone(), usuario.getEmail(), usuario.getSenha()
        );
    }
}
