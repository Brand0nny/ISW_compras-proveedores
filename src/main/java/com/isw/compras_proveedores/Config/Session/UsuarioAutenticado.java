package com.isw.compras_proveedores.Config.Session;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.isw.compras_proveedores.model.Usuario;

public class UsuarioAutenticado {
public static Usuario get() {
        Object obj = SessionContext.getUsuarioActual();
        if (obj instanceof Usuario usuario) {
            return usuario;
        }
        return null;
    }
    public static boolean esAdmin() {
        Usuario u = UsuarioAutenticado.get();
        return u != null && "ADMIN".equals(u.getRole());
    }

    public static void validarRol(String... rolesPermitidos) {
       Usuario u = get();
        if (u == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No autenticado");
        }
        boolean permitido = Arrays.stream(rolesPermitidos).anyMatch(r -> r.equals(u.getRole()));
        if (!permitido) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado");
        }
    }
}
