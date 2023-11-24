package com.example.service1.services;

import com.example.service1.models.Usuario;

import java.util.Optional;

public interface UsuarioService {
    public Optional<Usuario> findByUsuario(String usuario);
    public Usuario save(Usuario usuario);
    public void deleteById(Long id);
}
