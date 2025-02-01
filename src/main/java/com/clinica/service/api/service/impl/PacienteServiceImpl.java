package com.clinica.service.api.service.impl;

import java.sql.Timestamp;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.clinica.service.api.entity.Paciente;
import com.clinica.service.api.repository.PacienteRepository;
import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.request.PacienteRequest;
import com.clinica.service.api.response.PacienteResponse;
import com.clinica.service.api.service.PacienteService;
import com.clinica.service.api.response.CitaMedicaResponse;
import com.clinica.service.api.response.MedicoResponse;

/**
 * Servicio PacienteServiceImpl que implementa los metodos de la interfaz
 * PacienteService
 *
 * @see PacienteService.class
 * @author Diego Sanchez
 */
@Service
public class PacienteServiceImpl implements PacienteService {

    private PacienteRepository pacienteRepository;

    /**
     * Constructor para PacienteServiceImpl.
     *
     * @param pacienteRepository Repositorio para gestionar data de pacientes
     * 
     * @see PacienteRepository.class
     */
    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Valida la existencia del paciente relacionado con la cita medica, sino existe
     * lo crea y persiste
     *
     * @param citaMedicaRequest Objeto que contiene la informacion de la cita medica
     * @return PacienteRequest Objeto que contiene la informacion del paciente
     */
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

    /**
     * Obtiene las citas medicas futuras de un paciente
     *
     * @param id del paciente
     * @return PacienteResponse Objeto que contiene la informacion del paciente y
     *         sus citas medicas futuras
     */
    @Override
    public PacienteResponse obtenerCitasMedicasFutura(Long id) {
        Optional<Paciente> pacientePorId = pacienteRepository.findById(id);

        Optional<PacienteResponse> map = pacientePorId.map(paciente -> PacienteResponse.builder()
                .id(paciente.getId())
                .nombre(paciente.getNombre())
                .apellido(paciente.getApellido())
                .citasMedicas(
                        paciente.getCitasMedicas().stream()
                                .filter(citaMedica -> citaMedica.getFecha()
                                        .after(new Timestamp(System.currentTimeMillis())))
                                .map(citaMedica -> CitaMedicaResponse.builder()
                                        .id(citaMedica.getId())
                                        .cita(citaMedica.getCita())
                                        .fecha(citaMedica.getFecha())
                                        .estado(citaMedica.getEstado())
                                        .medico(MedicoResponse.builder()
                                                .id(citaMedica.getMedico().getId())
                                                .nombre(citaMedica.getMedico().getNombre())
                                                .apellido(citaMedica.getMedico().getApellido())
                                                .build())
                                        .build())
                                .collect(Collectors.toList()))
                .build());

        return map.isPresent() ? map.get() : null;
    }

}
