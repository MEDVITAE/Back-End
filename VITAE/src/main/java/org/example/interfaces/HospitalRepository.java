package org.example.interfaces;

import org.example.Domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {
    @Query(value = "SELECT * FROM Hospital as  h join endereco as e on h.id_Hospital = e.fk_Hospital where h.id_Hospital = :id",nativeQuery = true)
    RecuperaValoresDetalheHosp findByHospitalEndereco(int id);
}
