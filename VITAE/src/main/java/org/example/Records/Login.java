package org.example.Records;

import org.example.Enums.Usuarios.UserRole;

public record Login(String token, Long Id, UserRole userRole,String nome,int fkHospital) {
}
