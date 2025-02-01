package com.clinica.service.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.service.api.response.MedicoResponse;

import com.clinica.service.api.service.MedicoService;

/**
 * REST controller para manipular data de pacientes.
 *
 * 
 * @author Diego Sanchez
 */
@RestController
@RequestMapping("/api")
public class MedicoController {

    /**
     * Servicio para gestionar data de medicos.
     * 
     * @see MedicoService.class
     */
    private MedicoService medicoService;

    /**
     * Constructor para MedicoController.
     *
     * @param medicoService servicio para gestionar medicos
     */
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    /**
     * Endpoint para consultar citas pertenecientes a un medico.
     * 
     * @param id del paciente
     * @return ResponseEntity con estado http 200 y la información de las citas
     */
    @GetMapping("/medico/citasmedicas")
    public ResponseEntity<MedicoResponse> consultarCitasMedicas(@RequestParam Long id) {

        return ResponseEntity.ok(medicoService.consultarCitasMedicas(id));
    }

}
