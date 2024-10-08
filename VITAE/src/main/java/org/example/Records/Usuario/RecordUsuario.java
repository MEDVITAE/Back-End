package org.example.Records.Usuario;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import org.example.Domain.Caracteristicas;
import org.example.Domain.Enfermeira;
import org.example.Domain.Paciente;
import org.example.Domain.Usuario;
import org.example.Enums.Usuarios.UserRole;

public record RecordUsuario(

        String nome,
        String email,
        String senha,
        UserRole role,
        String cpf,
        int fkHospital,
        @OneToMany
        @JoinColumn
        Caracteristicas fkCaracteristicas
                            ) {

}
