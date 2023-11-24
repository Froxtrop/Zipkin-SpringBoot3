package com.example.service1.controllers;

import com.example.service1.models.Usuario;
import com.example.service1.services.UsuarioService;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    @PostMapping("/create")
    @Observed(
            name = "usuario.create",
            contextualName = "Controller",
            lowCardinalityKeyValues = {"userType", "userType1"}
    )
    public ResponseEntity<?> crear(@RequestBody Usuario usuario){
            return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @PutMapping("/update")
    @Observed(
            name = "usuario.update",
            contextualName = "Controller",
            lowCardinalityKeyValues = {"userType", "userType1"}
    )
    public ResponseEntity<?> actualizar(@RequestBody Usuario usuario){
            return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @GetMapping("/get/{user}")
    @Observed(
            name = "usuario.get",
            contextualName = "Controller",
            lowCardinalityKeyValues = {"userType", "userType1"}
    )
    public ResponseEntity<?> get(@PathVariable (value="user") String user) {
            Map<String, Object> map = new HashMap<>();

            //boolean existeUsuario = usuarioService.findByUsuario(user).isPresent();

            //if(existeUsuario) {
                Usuario usuario = usuarioService.findByUsuario(user).get();
                map.put("usuario", usuario.getUsuario());
                map.put("nombre", usuario.getNombre());
                map.put("apellido", usuario.getApellido());

                return ResponseEntity.ok(map);
            //}
    }

    @DeleteMapping("/delete/{user}")
    @Observed(
            name = "usuario.delete",
            contextualName = "Controller",
            lowCardinalityKeyValues = {"userType", "userType1"}
    )
    public ResponseEntity<?> borrar(@PathVariable(value="user") String user) {
        Usuario usuario = usuarioService.findByUsuario(user).get();
        usuarioService.deleteById(usuario.getId());
        return ResponseEntity.ok("Se borro el usuario");
    }
}
