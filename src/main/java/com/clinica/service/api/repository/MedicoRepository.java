package com.clinica.service.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.service.api.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Optional<Medico> findByNombreAndApellido(String nombre, String apellido);
}
