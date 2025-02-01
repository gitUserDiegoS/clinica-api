package com.clinica.service.api.service;

import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.request.MedicoRequest;
import com.clinica.service.api.response.MedicoResponse;

public interface MedicoService {

    MedicoRequest validarExistenciaMedico(CitaMedicaRequest citaMedicaRequest);

    MedicoResponse consultarCitasMedicas(Long id);

}
