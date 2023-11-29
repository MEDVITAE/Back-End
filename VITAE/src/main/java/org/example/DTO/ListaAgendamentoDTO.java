package org.example.DTO;

public class ListaAgendamentoDTO {
    private Integer IdAgenda;
    private Integer FkUsuario;
    private Integer FkHospital;
    private String Cpf;
    private String Nome;
    private String data;
    private String hora;

    public ListaAgendamentoDTO(Integer idAgenda, Integer fkUsuario, Integer fkHospital, String cpf, String nome, String data, String hora) {
        IdAgenda = idAgenda;
        FkUsuario = fkUsuario;
        FkHospital = fkHospital;
        Cpf = cpf;
        Nome = nome;
        this.data = data;
        this.hora = hora;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "ListaAgendamentoDTO{" +
                "IdAgenda=" + IdAgenda +
                ", FkUsuario=" + FkUsuario +
                ", FkHospital=" + FkHospital +
                ", Cpf='" + Cpf + '\'' +
                ", Nome='" + Nome + '\'' +
                ", data='" + data + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
