package com.clinica.service.api.controller.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.clinica.service.api.entity.Medico;
import com.clinica.service.api.entity.Paciente;
import com.clinica.service.api.repository.MedicoRepository;
import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.request.MedicoRequest;
import com.clinica.service.api.request.PacienteRequest;
import com.clinica.service.api.service.impl.MedicoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MedicoServiceImplTest {

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private MedicoServiceImpl medicoServiceImpl;

    private CitaMedicaRequest citaMedicaRequest;

    private Medico medico;

    private Paciente paciente;

    private MedicoRequest medicoRequest;

    private PacienteRequest pacienteRequest;

    @BeforeEach
    public void setUp() {

        medicoRequest = MedicoRequest.builder()
                .nombre("TestNombreMedico")
                .apellido("TestApellidoMedico")
                .build();

        pacienteRequest = PacienteRequest.builder()
                .nombre("TestNombrePaciente")
                .apellido("TestApellidoPaciente")
                .build();

        citaMedicaRequest = CitaMedicaRequest.builder()
                .cita("Cita de prueba")
                .fecha(new Timestamp(System.currentTimeMillis()))
                .estado("Activa")
                .medico(medicoRequest)
                .paciente(pacienteRequest)
                .build();

        medico = Medico.builder()
                .id(1L)
                .nombre("TestNombreMedico")
                .apellido("TestApellidoMedico")
                .build();
    }

    @Test
    public void testMedicoExiste() {
        when(medicoRepository.findByNombreAndApellido(any(String.class), any(String.class)))
                .thenReturn(Optional.of(medico));

        MedicoRequest result = medicoServiceImpl.validarExistenciaMedico(citaMedicaRequest);

        assertEquals(1L, result.getId());
        assertEquals("TestNombreMedico", result.getNombre());
        assertEquals("TestApellidoMedico", result.getApellido());
    }

    @Test
    public void testMedicoNoExiste() {
        when(medicoRepository.findByNombreAndApellido(anyString(), anyString()))
                .thenReturn(Optional.empty());

        when(medicoRepository.save(any(Medico.class)))
                .thenReturn(medico);

        MedicoRequest result = medicoServiceImpl.validarExistenciaMedico(citaMedicaRequest);

        assertEquals(1L, result.getId());
        assertEquals("TestNombreMedico", result.getNombre());
        assertEquals("TestApellidoMedico", result.getApellido());
    }

}