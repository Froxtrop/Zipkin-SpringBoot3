package com.example.service1.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String usuario;
    public String pass;
    public String nombre;
    public String apellido;
}
