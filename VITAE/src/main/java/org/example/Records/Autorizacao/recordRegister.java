package org.example.Records.Autorizacao;


import org.example.Enums.Usuarios.UserRole;

public record recordRegister(String email, String senha, UserRole role) {
}
