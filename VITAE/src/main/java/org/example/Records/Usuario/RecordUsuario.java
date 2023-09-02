package org.example.Records.Usuario;

import org.example.Domain.Usuario;
import org.example.Enums.Usuarios.UserRole;

public record RecordUsuario(
        Usuario usuario,
        String nome,
        String email,
        String senha,
        String telefone,
                            UserRole role
                            ) {}
