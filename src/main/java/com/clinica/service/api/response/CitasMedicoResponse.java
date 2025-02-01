package com.clinica.service.api.response;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CitasMedicoResponse {

    private Long id;

    private String cita;

    private Timestamp fecha;

    private String estado;

    private DatosPersonaResponse paciente;

}
