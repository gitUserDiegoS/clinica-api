package com.clinica.service.api.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clinica.service.api.entity.CitaMedica;
import com.clinica.service.api.entity.Medico;
import com.clinica.service.api.entity.Paciente;
import com.clinica.service.api.repository.CitaMedicaRepository;

import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.request.MedicoRequest;
import com.clinica.service.api.request.PacienteRequest;
import com.clinica.service.api.service.CitaMedicaService;
import com.clinica.service.api.service.MedicoService;
import com.clinica.service.api.service.PacienteService;

@Service
public class CitaMedicaServiceImpl implements CitaMedicaService {

        private CitaMedicaRepository citaMedicaRepository;

        private MedicoService medicoService;

        private PacienteService pacienteService;

        public CitaMedicaServiceImpl(CitaMedicaRepository citaMedicaRepository, MedicoService medicoService,
                        PacienteService pacienteService) {
                this.citaMedicaRepository = citaMedicaRepository;
                this.medicoService = medicoService;
                this.pacienteService = pacienteService;

        }

        @Override
        @Transactional
        public void registrarCitaMedica(CitaMedicaRequest citaMedicaRequest) {

                PacienteRequest pacienteRequest = pacienteService.validarExistenciaPaciente(citaMedicaRequest);

                MedicoRequest medicoRequest = medicoService.validarExistenciaMedico(citaMedicaRequest);

                citaMedicaRepository.save(CitaMedica.builder()
                                .cita(citaMedicaRequest.getCita())
                                .fecha(citaMedicaRequest.getFecha())
                                .estado(citaMedicaRequest.getEstado())
                                .paciente(Paciente.builder()
                                                .id(pacienteRequest.getId())
                                                .nombre(pacienteRequest.getNombre())
                                                .apellido(pacienteRequest.getApellido())
                                                .build())
                                .medico(Medico.builder()
                                                .id(medicoRequest.getId())
                                                .nombre(medicoRequest.getNombre())
                                                .apellido(medicoRequest.getApellido())
                                                .build())
                                .build());

        }

}
