package com.clinica.service.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CancelarCitaRequest {

    private Long citaId;

    private Long pacienteId;

}
