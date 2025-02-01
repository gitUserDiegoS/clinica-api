package com.clinica.service.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.service.api.response.PacienteResponse;
import com.clinica.service.api.service.PacienteService;

/**
 * REST controller para manipular data de pacientes.
 *
 * 
 * @author Diego Sanchez
 */
@RestController
@RequestMapping("/api")
public class PacienteController {

    /**
     * Servicio para gestionar pacientes.
     * 
     * @see PacienteService.class
     */
    private PacienteService pacienteService;

    /**
     * Constructor para pacienteService.
     *
     * @param PacienteService servicio para gestionar pacientes
     */
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    /**
     * Endpoint para consultar las citas médicas futuras de un paciente.
     * 
     * @param id del paciente
     * @return ResponseEntity con estado http 200 y la información de las citas
     */
    @GetMapping("/paciente/citasmedicasfuturas")
    public ResponseEntity<PacienteResponse> consultarCitasMedicasFuturas(@RequestParam Long id) {

        return ResponseEntity.ok(pacienteService.obtenerCitasMedicasFutura(id));
    }

}
