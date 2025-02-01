package com.clinica.service.api.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.clinica.service.api.entity.Paciente;
import com.clinica.service.api.repository.PacienteRepository;
import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.request.PacienteRequest;
import com.clinica.service.api.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

    private PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public PacienteRequest validarExistenciaPaciente(CitaMedicaRequest citaMedicaRequest) {

        Optional<Paciente> paciente = pacienteRepository.findByNombreAndApellido(
                citaMedicaRequest.getPaciente().getNombre(),
                citaMedicaRequest.getPaciente().getApellido());

        if (!paciente.isPresent()) {

            Paciente save = pacienteRepository.save(Paciente.builder()
                    .nombre(citaMedicaRequest.getPaciente().getNombre())
                    .apellido(citaMedicaRequest.getPaciente().getApellido())
                    .build());

            return PacienteRequest.builder()
                    .id(save.getId())
                    .nombre(save.getNombre())
                    .apellido(save.getApellido())
                    .build();

        }
        return PacienteRequest.builder()
                .id(paciente.get().getId())
                .nombre(paciente.get().getNombre())
                .apellido(paciente.get().getApellido())
                .build();
    }

}
