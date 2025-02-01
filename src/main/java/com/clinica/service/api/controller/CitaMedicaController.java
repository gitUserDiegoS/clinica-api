package com.clinica.service.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.service.api.request.CitaMedicaRequest;
import com.clinica.service.api.service.CitaMedicaService;

@RestController
@RequestMapping("/api")
public class CitaMedicaController {

    private CitaMedicaService citaMedicaService;

    public CitaMedicaController(CitaMedicaService citaMedicaService) {
        this.citaMedicaService = citaMedicaService;
    }

    @PostMapping("/citasmedicas")
    public ResponseEntity<String> registrarCita(@RequestBody CitaMedicaRequest citaMedicaRequest) {

        citaMedicaService.registrarCitaMedica(citaMedicaRequest);

        return ResponseEntity.created(null).body("Cita registrada con exito");
    }

}
