package org.example.Records.Usuario;

import org.example.Domain.Enfermeira;
import org.example.Domain.Paciente;
import org.example.Domain.Recepcao;
import org.example.Domain.Usuario;
import org.example.Enums.Usuarios.UserRole;

public record AtualizarUser (

        String nome,
        String email,

        UserRole role,
        String cpf,
        int fkHospital,
        String senha


)
{


}
