package org.example.Records.Usuario;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import org.example.Domain.Caracteristicas;
import org.example.Domain.Usuario;
import org.example.Enums.Usuarios.UserRole;

public record RecordUsuario(

        String nome,
        String email,
        String senha,
        UserRole role,
        @OneToMany
        @JoinColumn
        Caracteristicas fkCaracteristicas
                            ) {}
