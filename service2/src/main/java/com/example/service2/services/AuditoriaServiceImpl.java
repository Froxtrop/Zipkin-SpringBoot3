package com.example.service2.services;

import com.example.service2.models.Auditoria;
import com.example.service2.repositories.AuditoriaRepository;
import io.micrometer.observation.annotation.Observed;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class AuditoriaServiceImpl implements AuditoriaService{
    private final AuditoriaRepository auditoriaRepository;

    @Override
    @Observed(
            name = "auditoria.create",
            contextualName = "Service",
            lowCardinalityKeyValues = {"userType", "create"}
    )
    public Auditoria save(Auditoria auditoria){
        return auditoriaRepository.save(auditoria);
    }
}
