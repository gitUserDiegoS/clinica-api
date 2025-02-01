package com.clinica.service.api.controller.service.impl;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import com.clinica.service.api.entity.CitaMedica;

import com.clinica.service.api.repository.CitaMedicaRepository;
import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.request.MedicoRequest;
import com.clinica.service.api.request.PacienteRequest;
import com.clinica.service.api.service.MedicoService;
import com.clinica.service.api.service.PacienteService;
import com.clinica.service.api.service.impl.CitaMedicaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CitaMedicaServiceImplTest {

    @Mock
    private CitaMedicaRepository citaMedicaRepository;

    @Mock
    private MedicoService medicoService;

    @Mock
    private PacienteService pacienteService;

    @InjectMocks
    private CitaMedicaServiceImpl citaMedicaServiceImpl;

    private CitaMedicaRequest citaMedicaRequest;
    private PacienteRequest pacienteRequest;
    private MedicoRequest medicoRequest;
    private CitaMedica citaMedica;

    @BeforeEach
    void setUp() {
        pacienteRequest = PacienteRequest.builder()
                .id(1L)
                .nombre("Diego")
                .apellido("Sanchez")
                .build();

        medicoRequest = MedicoRequest.builder()
                .id(1L)
                .nombre("Sandra")
                .apellido("Rodriguez")
                .build();

        citaMedicaRequest = CitaMedicaRequest.builder()
                .id(1L)
                .cita("Consulta General")
                .fecha(Timestamp.valueOf("2023-10-10 00:00:00"))
                .estado("Asignada")
                .paciente(pacienteRequest)
                .medico(medicoRequest)
                .build();

        citaMedica = CitaMedica.builder().build();

    }

    @Test
    void testRegistrarCitaMedica() {
        when(pacienteService.validarExistenciaPaciente(any(CitaMedicaRequest.class))).thenReturn(pacienteRequest);
        when(medicoService.validarExistenciaMedico(any(CitaMedicaRequest.class))).thenReturn(medicoRequest);

        when(citaMedicaRepository.save(any(CitaMedica.class))).thenReturn(citaMedica);

        citaMedicaServiceImpl.registrarCitaMedica(citaMedicaRequest);

        verify(pacienteService).validarExistenciaPaciente(citaMedicaRequest);
        verify(medicoService).validarExistenciaMedico(citaMedicaRequest);
        verify(citaMedicaRepository).save(any(CitaMedica.class));
    }
}