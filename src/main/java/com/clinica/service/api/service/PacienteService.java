package com.clinica.service.api.service;

import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.request.PacienteRequest;
import com.clinica.service.api.response.PacienteResponse;

public interface PacienteService {

    PacienteRequest validarExistenciaPaciente(CitaMedicaRequest citaMedicaRequest);

    PacienteResponse obtenerCitasMedicasFutura(Long id);

}
