package com.clinica.service.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.ResponseEntity;
import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.request.MedicoRequest;
import com.clinica.service.api.request.PacienteRequest;
import com.clinica.service.api.service.CitaMedicaService;

@ExtendWith(MockitoExtension.class)
public class CitaMedicaControllerTest {

    @Mock
    private CitaMedicaService citaMedicaService;

    @InjectMocks
    private CitaMedicaController citaMedicaController;

    private CitaMedicaRequest citaMedicaRequest;

    @BeforeEach
    void setUp() {
        citaMedicaRequest = CitaMedicaRequest.builder().id(1L).cita("Cita de prueba")
                .fecha(new Timestamp(System.currentTimeMillis()))
                .estado("Activa")
                .paciente(PacienteRequest.builder()
                        .id(1L).nombre("Paciente de prueba")
                        .build())
                .medico(MedicoRequest.builder()
                        .id(1L).nombre("Medico de prueba")
                        .build())
                .build();
    }

    @Test
    void testRegistrarCita() {
        doNothing().when(citaMedicaService).registrarCitaMedica(any(CitaMedicaRequest.class));

        ResponseEntity<String> response = citaMedicaController.registrarCita(citaMedicaRequest);

        verify(citaMedicaService).registrarCitaMedica(citaMedicaRequest);
        assertEquals("Cita registrada con exito", response.getBody());
        assertEquals(201, response.getStatusCode().value());
    }
}