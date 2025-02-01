package com.clinica.service.api.controller;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.ResponseEntity;

import com.clinica.service.api.response.PacienteResponse;

import com.clinica.service.api.service.PacienteService;

@ExtendWith(MockitoExtension.class)
public class PacienteControllerTest {

    @Mock
    private PacienteService pacienteService;

    @InjectMocks
    private PacienteController pacienteController;

    private PacienteResponse pacienteResponse;

    @BeforeEach
    void setUp() {
        pacienteResponse = PacienteResponse.builder()
                .id(1L)
                .nombre("nombrePaciente")
                .citasMedicas(new ArrayList<>()).build();

    }

    @Test
    void testConsultarCitasMedicasFuturas() {

        when(pacienteService.obtenerCitasMedicasFutura(any(long.class))).thenReturn(pacienteResponse);

        ResponseEntity<PacienteResponse> response = pacienteController.consultarCitasMedicasFuturas(1L);

        verify(pacienteService).obtenerCitasMedicasFutura(1L);
        assertEquals(200, response.getStatusCode().value());
    }

}
