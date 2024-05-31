package org.example.interfaces;

import org.example.Enums.Usuarios.UserRole;

public interface RecuperaValoresFuncionarioHospital {
     String getEmail();
     String getSenha();
     UserRole getRole();
     String getNome();
     Integer  getFk_Hospital();
     String getCpf();
     String getEmailHospital();
     String getNomeHospital();
     String getSenhaHospital();
     String getCnpj();

}
