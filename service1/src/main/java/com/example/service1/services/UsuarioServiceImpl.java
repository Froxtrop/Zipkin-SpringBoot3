package com.example.service1.services;


import com.example.service1.models.Auditoria;
import com.example.service1.models.Usuario;
import com.example.service1.repositories.UsuarioRepository;
import io.micrometer.observation.annotation.Observed;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService
{
    private final UsuarioRepository usuarioRepository;
    private final WebClient webClient;
    @Value("${service2.url}")
    private String url;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, WebClient webClient) {
        this.usuarioRepository = usuarioRepository;
        this.webClient = webClient;
    }

    @Override
    @Observed(
            name = "usuario.get",
            contextualName = "Service",
            lowCardinalityKeyValues = {"userType", "userType1"}
    )
    public Optional<Usuario> findByUsuario(String usuario){
        return usuarioRepository.findByUsuario(usuario);
    }
    @Override
    @Observed(
            name = "usuario.save",
            contextualName = "Service",
            lowCardinalityKeyValues = {"userType", "userType1"}
    )
    public Usuario save(Usuario usuario){
        Usuario user = usuarioRepository.save(usuario);

        Auditoria auditoria = new Auditoria();
        auditoria.comentario = "Usuario creado";
        auditoria.fecha = LocalDateTime.now();

        var response = webClient.post()
                .uri(url+"/create")
                .bodyValue(auditoria)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(new RuntimeException()))
                .onStatus(HttpStatusCode::is5xxServerError, error -> Mono.error(new RuntimeException()))
                .bodyToMono(Auditoria.class)
                .block();

        System.out.println(response);

        return user;
    }
    @Override
    @Observed(
            name = "usuario.delete",
            contextualName = "Service",
            lowCardinalityKeyValues = {"userType", "userType1"}
    )
    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }
}
