package com.isw.compras_proveedores.Config.Session;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@Component
public class SessionInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler)
            throws Exception {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    Object usuario = session.getAttribute("usuario");
                    if (usuario != null) {
                        SessionContext.setUsuarioActual(usuario);
                        return true;
                    }
                }
            
                // Excepciones: permitir login y register sin sesión
                String uri = request.getRequestURI();
                if (uri.contains("/login") || uri.contains("/register")) {
                    return true;
                }
            
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("No autorizado: sesión inválida o inexistente.");
                return false;
    }


}

