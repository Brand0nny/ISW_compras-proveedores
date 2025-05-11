package com.isw.compras_proveedores.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isw.compras_proveedores.Config.Session.SessionContext;
import com.isw.compras_proveedores.Config.Session.UsuarioAutenticado;
import com.isw.compras_proveedores.DTO.UsuarioDTO;
import com.isw.compras_proveedores.model.Usuario;
import com.isw.compras_proveedores.service.UsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/")
    public ResponseEntity<Iterable<UsuarioDTO>> getUsuarios(){
        UsuarioAutenticado.validarRol("ADMIN");
        Object usuarioAuth = SessionContext.getUsuarioActual();
        System.out.println(usuarioAuth.toString());
        System.out.println(usuarioAuth);
        return ResponseEntity.ok().body(usuarioService.getAllUsuarios());
    
    } 
    @PostMapping("/register")
    public ResponseEntity<String> registerUsuario(@RequestBody Usuario usuario){
        UsuarioAutenticado.validarRol("ADMIN");

        try {
            usuarioService.registerUsuario(usuario);
            return ResponseEntity.status(201).body("Usuario registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Algo salió mal al intentar registrar el usuario " + e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> getUsuarioMe(){
        UsuarioDTO usuario = usuarioService.getUsuario(UsuarioAutenticado.get().getUsuarioId());
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Long id){
        UsuarioAutenticado.validarRol("ADMIN");

        UsuarioDTO usuario = usuarioService.getUsuario(id);
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
    @PostMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        try {
            UsuarioAutenticado.validarRol("ADMIN");
            usuarioService.updateUsuario(id, usuarioDTO);
            return ResponseEntity.ok().body("Usuario actualizado con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
