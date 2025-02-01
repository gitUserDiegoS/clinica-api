package com.clinica.service.api.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.clinica.service.api.entity.Medico;
import com.clinica.service.api.repository.MedicoRepository;
import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.request.MedicoRequest;
import com.clinica.service.api.service.MedicoService;

@Service
public class MedicoServiceImpl implements MedicoService {

    private MedicoRepository medicoRepository;

    public MedicoServiceImpl(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

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
