package org.example.Enums.Usuarios;

public enum UserRole {
    ADMIN("admin"),
    PACIENTE("paciente"),
    RECEPCAO("recepcao"),
    ENFERMEIRA("enfermeira");
    private String role;

    UserRole(String role){
        this.role = role;
    }
    public String getRole(String role){
        return this.role = role;
    }
}
