package com.isw.compras_proveedores.service;

import com.isw.compras_proveedores.model.Factura;
import com.isw.compras_proveedores.model.OrdenCompra;

public interface OrdenCompraService {
public void enviarOrdenPorCorreo(Long ordenCompraId);
public void aprobarOrdenPorCorreo(Long id);
public void rechazarOrdenPorCorreo(Long id);
public void notificarEnvioOrden(Long id);
public Factura confirmarRecepcion(Long ordenId);
}
