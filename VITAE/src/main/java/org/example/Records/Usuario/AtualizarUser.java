package org.example.Records.Usuario;

import org.example.Domain.Usuario;

public record AtualizarUser (
        String nome,
        String email,
        String senha,
        String telefone

)
{
    public AtualizarUser(Usuario usuario){
        this(
                usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getTelefone()
        );
    }
}
