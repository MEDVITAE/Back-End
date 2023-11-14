package org.example.DTO;

public class CepsDTo {
    private String cep;
    private Long fkHospital;

    public CepsDTo(String cep, Long fkHospital) {
        this.cep = cep;
        this.fkHospital = fkHospital;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getFkHospital() {
        return fkHospital;
    }

    public void setFkHospital(Long fkHospital) {
        this.fkHospital = fkHospital;
    }

    @Override
    public String toString() {
        return "CepsDTo{" +
                "cep='" + cep + '\'' +
                ", fkHospital=" + fkHospital +
                '}';
    }
}
