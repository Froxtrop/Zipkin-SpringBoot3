package com.example.service1.repositories;

import com.example.service1.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByUsuario(String usuario);
    public Optional<Usuario> findByUsuarioAndApellido(String usuario, String apellido);
}
