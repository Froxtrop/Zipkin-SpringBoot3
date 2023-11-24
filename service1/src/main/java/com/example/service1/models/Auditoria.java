package com.example.service1.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Auditoria {
    private long id;
    public String comentario;
    public LocalDateTime fecha;
}
