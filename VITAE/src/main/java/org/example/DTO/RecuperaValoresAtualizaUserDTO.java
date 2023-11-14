package org.example.DTO;

public class RecuperaValoresAtualizaUserDTO {
    private String email;

    public RecuperaValoresAtualizaUserDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "RecuperaValoresAtualizaUserDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
