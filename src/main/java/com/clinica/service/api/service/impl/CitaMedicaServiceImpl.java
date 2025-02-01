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

/**
 * Servicio CitaMedicaServiceImpl que implementa los metodos de la interfaz
 *
 * @see CitaMedicaService.class
 * @author Diego Sanchez
 */
@Service
public class CitaMedicaServiceImpl implements CitaMedicaService {

        private CitaMedicaRepository citaMedicaRepository;

        private MedicoService medicoService;

        private PacienteService pacienteService;

        /**
         * Constructor para CitaMedicaServiceImpl.
         *
         * @param citaMedicaRepository Repositorio para gestionar citas medicas @see
         *                             citaMedicaRepository.class
         * @param medicoService        Servicio para gestionar data de medicos @see
         *                             MedicoService.class
         * @param pacienteService      Servicio para gestionar y validar data de
         *                             pacientes @see PacienteService.class
         */
        public CitaMedicaServiceImpl(CitaMedicaRepository citaMedicaRepository, MedicoService medicoService,
                        PacienteService pacienteService) {
                this.citaMedicaRepository = citaMedicaRepository;
                this.medicoService = medicoService;
                this.pacienteService = pacienteService;

        }

        /**
         * Registra citas medicas, valida la existencia del paciente y del medico
         * relacionado con la cita medica, sino existe lo crea y persiste la cita
         *
         * @param citaMedicaRequest Objeto que contiene la informacion de la cita medica
         * @throws EntityNotFoundException si el paciente o medico no existen
         */
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
