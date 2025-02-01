package com.clinica.service.api.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicoResponse {

    private Long id;

    private String nombre;

    private String apellido;

    List<CitasMedicoResponse> citasMedicas;

}
