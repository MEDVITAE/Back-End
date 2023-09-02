package org.example.Records.Usuario;

import org.example.Domain.Usuario;
import org.example.Enums.Usuarios.UserRole;

public record RecordUsuario(

        String nome,
        String email,
        String senha,
        UserRole role,
        Long fkCaracteristicas
                            ) {}
