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

import com.clinica.service.api.response.MedicoResponse;

import com.clinica.service.api.service.MedicoService;

@ExtendWith(MockitoExtension.class)
public class MedicoControllerTest {

    @Mock
    private MedicoService medicoService;

    @InjectMocks
    private MedicoController medicoController;

    private MedicoResponse medicoResponse;

    @BeforeEach
    void setUp() {
        medicoResponse = MedicoResponse.builder()
                .id(1L)
                .nombre("nombreMedico")
                .citasMedicas(new ArrayList<>()).build();

    }

    @Test
    void testConsultarCitasMedicas() {

        when(medicoService.consultarCitasMedicas(any(long.class))).thenReturn(medicoResponse);

        ResponseEntity<MedicoResponse> response = medicoController.consultarCitasMedicas(1L);

        verify(medicoService).consultarCitasMedicas(1L);
        assertEquals(200, response.getStatusCode().value());
    }

}
