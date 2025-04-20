package com.isw.compras_proveedores.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isw.compras_proveedores.Config.Session.UsuarioAutenticado;
import com.isw.compras_proveedores.model.Usuario;
import com.isw.compras_proveedores.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping
public class AuthController {
    @Autowired
    UsuarioService usuarioService;

     @PostMapping("/register")
    public ResponseEntity<String> registerUsuario(@RequestBody Usuario usuario){
        try {
            
            usuarioService.registerUsuario(usuario);
            return ResponseEntity.status(201).body("Usuario registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Algo salió mal al intentar registrar el usuario " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@Valid @RequestBody Map<String, String> log, HttpSession session){        
        try {
            String username = log.get("username");
            String password = log.get("password");
            if (username == null || password == null) {
                return ResponseEntity.badRequest().body("Faltan credenciales");
            }
            Usuario usuario = usuarioService.logUsuario(username, password);
            session.setAttribute("usuario", usuario);
            return ResponseEntity.ok().body("Logged in");    
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); 
        if (session != null) {
            session.invalidate(); 
        }
        return ResponseEntity.ok("Sesión cerrada correctamente");
}

}
