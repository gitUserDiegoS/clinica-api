package com.clinica.service.api.repository;

import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.service.api.entity.CitaMedica;

@Repository
public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long> {

    Optional<CitaMedica> findByIdAndPacienteId(Long id, Long pacienteId);

    List<CitaMedica> findByfechaAndMedicoId(Timestamp fecha, Long medicoIdLong);
}
