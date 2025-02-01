package com.clinica.service.api.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.service.api.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByNombreAndApellido(String nombre, String apellido);

}
