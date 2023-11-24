package com.example.service2.controllers;

import com.example.service2.models.Auditoria;
import com.example.service2.services.AuditoriaService;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auditoria")
@AllArgsConstructor
public class AuditoriaController {
    private final AuditoriaService auditoriaService;
    @PostMapping("/create")
    @Observed(
            name = "usuario.save",
            contextualName = "Auditoria controller",
            lowCardinalityKeyValues = {"userType", "userType1"}
    )
    public ResponseEntity<?> crear(@RequestBody Auditoria auditoria){
            return ResponseEntity.ok(auditoriaService.save(auditoria));
    }
}
