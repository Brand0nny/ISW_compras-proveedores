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
    public boolean preHandle(@Nonnull HttpServletRequest request,
                             @Nonnull HttpServletResponse response,
                             @Nonnull Object handler) throws Exception {
        // 1) Deja pasar todos los OPTIONS (preflight CORS)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        // 2) Si ya hay sesión y atributo "usuario", continúa
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            SessionContext.setUsuarioActual(session.getAttribute("usuario"));
            return true;
        }

        // 3) Excepciones: login y register no requieren sesión
        String uri = request.getRequestURI();
        if (uri.contains("/login") || uri.contains("/register")) {
            return true;
        }

        // 4) Si no cumple nada de lo anterior, rechaza
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("No autorizado: sesión inválida o inexistente.");
        return false;
    }
}

