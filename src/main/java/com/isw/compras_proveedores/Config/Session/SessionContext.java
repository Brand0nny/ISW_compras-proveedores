package com.isw.compras_proveedores.Config.Session;

public class SessionContext {
private static final ThreadLocal<Object> usuarioActual = new ThreadLocal<>();

    public static void setUsuarioActual(Object usuario) {
        usuarioActual.set(usuario);
    }

    public static Object getUsuarioActual() {
        return usuarioActual.get();
    }

    public static void limpiar() {
        usuarioActual.remove();
    }

}
