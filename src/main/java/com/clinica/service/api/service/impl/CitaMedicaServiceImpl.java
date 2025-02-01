package com.clinica.service.api.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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

import jakarta.persistence.EntityNotFoundException;

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

                validarHorayMedico(citaMedicaRequest.getFecha(), medicoRequest.getId());

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

        /**
         * Cancela una cita medica, valida que la cita medica exista y pertenezca al
         * paciente
         *
         * @param id         identificador de la cita medica
         * @param pacienteId identificador del paciente
         */
        @Override
        public void cancelarCitaMedica(Long id, Long pacienteId) {
                Optional<CitaMedica> citaMedicaOptional = citaMedicaRepository.findByIdAndPacienteId(id, pacienteId);
                citaMedicaOptional.ifPresent(citaMedica -> {
                        citaMedica.setEstado("Cancelada");
                        citaMedicaRepository.save(citaMedica);
                });

        }

        /**
         * Consulta si un medico ya tiene cita medica en una hora igual a la solicitada
         *
         * @param fecha fecha de la cita medica
         * @param id    identificador del medico
         * @return MedicoResponse Objeto que contiene la informacion del medico y sus
         *         citas medicas
         */
        @Override
        public CitaMedicaService validarHorayMedico(Timestamp fecha, Long id) {

                List<CitaMedica> byfechaAndMedicoId = citaMedicaRepository.findByfechaAndMedicoId(fecha, id);

                long count = byfechaAndMedicoId.stream().count();
                if (count > 0) {
                        throw new IllegalArgumentException("El medico ya tiene una cita en esa fecha y hora");
                }
                return this;
        }

}
