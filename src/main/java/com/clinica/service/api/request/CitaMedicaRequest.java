package com.clinica.service.api.request;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CitaMedicaRequest {

    private Long id;

    private String cita;

    private Timestamp fecha;

    private String estado;

    private PacienteRequest paciente;

    private MedicoRequest medico;
}
