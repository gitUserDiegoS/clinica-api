package com.clinica.service.api.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.clinica.service.api.entity.Medico;
import com.clinica.service.api.repository.MedicoRepository;
import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.request.MedicoRequest;
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

}
