package com.clinica.service.api.service;

import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.request.PacienteRequest;

public interface PacienteService {

    PacienteRequest validarExistenciaPaciente(CitaMedicaRequest citaMedicaRequest);

}
