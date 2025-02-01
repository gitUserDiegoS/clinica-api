package com.clinica.service.api.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PacienteRequest {

    private Long id;

    private String nombre;

    private String apellido;
    
}
