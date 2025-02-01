package com.clinica.service.api.service.impl;


import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.clinica.service.api.entity.Medico;

import com.clinica.service.api.repository.MedicoRepository;
import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.request.MedicoRequest;

import com.clinica.service.api.response.CitasMedicoResponse;
import com.clinica.service.api.response.MedicoResponse;
import com.clinica.service.api.response.DatosPersonaResponse;

import com.clinica.service.api.service.MedicoService;

/**
 * Servicio MedicoServiceImpl que implementa los metodos de la interfaz
 * MedicoService
 *
 * @see MedicoService.class
 * @author Diego Sanchez
 */
@Service
public class MedicoServiceImpl implements MedicoService {

    private MedicoRepository medicoRepository;

    /**
     * Constructor para MedicoServiceImpl.
     * 
     * @param medicoRepository Repositorio para gestionar data de medicos
     * 
     * @see MedicoRepository.class
     */
    public MedicoServiceImpl(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    /**
     * Valida la existencia del medico relacionado con la cita medica, sino existe
     * lo crea y persiste el medico
     *
     * @param citaMedicaRequest Objeto que contiene la informacion de la cita medica
     * @return MedicoRequest Objeto que contiene la informacion del medico
     */
    @Override
    public MedicoRequest validarExistenciaMedico(CitaMedicaRequest citaMedicaRequest) {

        Optional<Medico> medico = medicoRepository.findByNombreAndApellido(citaMedicaRequest.getMedico().getNombre(),
                citaMedicaRequest.getMedico().getApellido());

        if (!medico.isPresent()) {
            Medico save = medicoRepository.save(Medico.builder()
                    .nombre(citaMedicaRequest.getMedico().getNombre())
                    .apellido(citaMedicaRequest.getMedico().getApellido())
                    .build());

            return MedicoRequest.builder()
                    .id(save.getId())
                    .nombre(save.getNombre())
                    .apellido(save.getApellido())
                    .build();

        }
        return MedicoRequest.builder()
                .id(medico.get().getId())
                .nombre(medico.get().getNombre())
                .apellido(medico.get().getApellido())
                .build();
    }

    @Override
    public MedicoResponse consultarCitasMedicas(Long id) {

        Optional<Medico> medicoPorId = medicoRepository.findById(id);

        Optional<MedicoResponse> map = medicoPorId.map(medico -> MedicoResponse.builder()
                .id(medico.getId())
                .nombre(medico.getNombre())
                .apellido(medico.getApellido())
                .citasMedicas(
                        medico.getCitasMedicas().stream()
                                .map(citaMedica -> CitasMedicoResponse.builder()
                                        .id(citaMedica.getId())
                                        .cita(citaMedica.getCita())
                                        .fecha(citaMedica.getFecha())
                                        .estado(citaMedica.getEstado())
                                        .paciente(DatosPersonaResponse.builder()
                                                .id(citaMedica.getPaciente().getId())
                                                .nombre(citaMedica.getPaciente().getNombre())
                                                .apellido(citaMedica.getPaciente().getApellido())
                                                .build())
                                        .build())
                                .collect(Collectors.toList()))
                .build());

        return map.isPresent() ? map.get() : null;
    }

}
