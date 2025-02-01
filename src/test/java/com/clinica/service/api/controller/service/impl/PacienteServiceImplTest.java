package com.clinica.service.api.controller.service.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.clinica.service.api.entity.Paciente;

import com.clinica.service.api.repository.PacienteRepository;
import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.request.MedicoRequest;
import com.clinica.service.api.request.PacienteRequest;
import com.clinica.service.api.response.PacienteResponse;
import com.clinica.service.api.service.impl.PacienteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceImplTest {

        @Mock
        private PacienteRepository pacienteRepository;

        @InjectMocks
        private PacienteServiceImpl pacienteServiceImpl;

        private CitaMedicaRequest citaMedicaRequest;

        private Paciente paciente;

        private MedicoRequest medicoRequest;

        private PacienteRequest pacienteRequest;

        private Optional<Paciente> datosPaciente;

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

                paciente = Paciente.builder()
                                .id(1L)
                                .nombre("TestNombrePaciente")
                                .apellido("TestApellidopaciente")
                                .citasMedicas(new ArrayList<>())
                                .build();

                datosPaciente = Optional.of(paciente);
        }

        @Test
        public void testPacienteExiste() {
                when(pacienteRepository.findByNombreAndApellido(any(String.class), any(String.class)))
                                .thenReturn(Optional.of(paciente));

                PacienteRequest resultado = pacienteServiceImpl.validarExistenciaPaciente(citaMedicaRequest);

                assertEquals(1L, resultado.getId());
                assertEquals("TestNombrePaciente", resultado.getNombre());
                assertEquals("TestApellidopaciente", resultado.getApellido());
        }

        @Test
        public void testPacienteNoExiste() {
                when(pacienteRepository.findByNombreAndApellido(anyString(), anyString()))
                                .thenReturn(Optional.empty());

                when(pacienteRepository.save(any(Paciente.class)))
                                .thenReturn(paciente);

                PacienteRequest result = pacienteServiceImpl.validarExistenciaPaciente(citaMedicaRequest);

                assertEquals(1L, result.getId());
                assertEquals("TestNombrePaciente", result.getNombre());
                assertEquals("TestApellidopaciente", result.getApellido());
        }

        @Test
        public void testObtenerCitasMedicasFutura() {
                when(pacienteRepository.findById(any(Long.class)))
                                .thenReturn(Optional.of(paciente)).thenReturn(datosPaciente);

                PacienteResponse result = pacienteServiceImpl.obtenerCitasMedicasFutura(1L);

                assertEquals(1L, result.getId());
                assertEquals("TestNombrePaciente", result.getNombre());
                assertEquals("TestApellidopaciente", result.getApellido());
        }

}
