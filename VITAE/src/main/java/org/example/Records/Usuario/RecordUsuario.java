package org.example.Records.Usuario;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import org.example.Domain.Caracteristicas;
import org.example.Enums.Usuarios.UserRole;

public record RecordUsuario(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotBlank
        UserRole role,
        @OneToMany
        @JoinColumn
        Caracteristicas fkCaracteristicas
                            ) {}
