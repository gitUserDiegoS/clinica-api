package com.clinica.service.api.service;

import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.request.MedicoRequest;

public interface MedicoService {

    MedicoRequest validarExistenciaMedico(CitaMedicaRequest citaMedicaRequest);

}
