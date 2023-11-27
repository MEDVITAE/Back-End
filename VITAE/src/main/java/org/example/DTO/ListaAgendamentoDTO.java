package org.example.DTO;

public class ListaAgendamentoDTO {
    private Integer IdAgenda;
    private Integer FkUsuario;
    private Integer FkHospital;
    private String Cpf;
    private String Nome;

    public ListaAgendamentoDTO(Integer idAgenda, Integer fkUsuario, Integer fkHospital, String cpf, String nome) {
        IdAgenda = idAgenda;
        FkUsuario = fkUsuario;
        FkHospital = fkHospital;
        Cpf = cpf;
        Nome = nome;
    }

    public Integer getIdAgenda() {
        return IdAgenda;
    }

    public void setIdAgenda(Integer idAgenda) {
        IdAgenda = idAgenda;
    }

    public Integer getFkUsuario() {
        return FkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        FkUsuario = fkUsuario;
    }

    public Integer getFkHospital() {
        return FkHospital;
    }

    public void setFkHospital(Integer fkHospital) {
        FkHospital = fkHospital;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    @Override
    public String toString() {
        return "listaAgendametoDTO{" +
                "IdAgenda=" + IdAgenda +
                ", FkUsuario=" + FkUsuario +
                ", FkHospital=" + FkHospital +
                ", Cpf='" + Cpf + '\'' +
                ", Nome='" + Nome + '\'' +
                '}';
    }
}
