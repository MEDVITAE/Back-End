package org.example.Records.Autorizacao;

import lombok.Getter;

@Getter
public class RecordAuth{
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    private String senha;
}