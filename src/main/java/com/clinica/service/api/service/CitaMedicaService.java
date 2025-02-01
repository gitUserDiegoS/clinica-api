package com.clinica.service.api.service;

import java.sql.Timestamp;

import com.clinica.service.api.request.CitaMedicaRequest;


public interface CitaMedicaService {

    public void registrarCitaMedica(CitaMedicaRequest citaMedicaRequest);

    public void cancelarCitaMedica(Long id, Long pacienteId);

    CitaMedicaService validarHorayMedico(Timestamp fecha, Long id);


}
