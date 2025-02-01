package com.clinica.service.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.service.api.request.CancelarCitaRequest;
import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.service.CitaMedicaService;

/**
 * REST controller para registro de citas medicas.
 * 
 * @author Diego Sanchez
 */
@RestController
@RequestMapping("/api")
public class CitaMedicaController {

    /**
     * Servicio para gestionar pacientes.
     * 
     * @see CitaMedicaService.class
     */
    private CitaMedicaService citaMedicaService;

    /**
     * Constructor para CitaMedicaController.
     *
     * @param citaMedicaService servicio para gestionar citas medicas
     */
    public CitaMedicaController(CitaMedicaService citaMedicaService) {
        this.citaMedicaService = citaMedicaService;
    }

    /**
     * Registro de una nueva cita medica.
     *
     * @param citaMedicaRequest con los datos de la cita medica
     * @return ResponseEntity con estado http 201 y mensaje de exito
     */
    @PostMapping("/citasmedicas")
    public ResponseEntity<String> registrarCita(@RequestBody CitaMedicaRequest citaMedicaRequest) {
        citaMedicaService.registrarCitaMedica(citaMedicaRequest);
        return ResponseEntity.created(null).body("Cita registrada con exito");
    }

    /**
     * Cancelación de una cita medica.
     *
     * @param CancelarCitaRequest citaId y pacienteId a cancelar
     * @return ResponseEntity con estado http 200 y mensaje de exito
     */
    @PostMapping("/citasmedicas/cancelar")
    public ResponseEntity<String> cancelarCita(@RequestBody CancelarCitaRequest cancelarCitaRequest) {
        citaMedicaService.cancelarCitaMedica(cancelarCitaRequest.getCitaId(), cancelarCitaRequest.getPacienteId());
        return ResponseEntity.ok("Cita cancelada con exito");
    }
}
