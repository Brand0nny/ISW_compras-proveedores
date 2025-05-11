package com.isw.compras_proveedores.service;

import com.isw.compras_proveedores.DTO.EvaluacionCalidadDTO;
import com.isw.compras_proveedores.DTO.RecepcionDTO;
import com.isw.compras_proveedores.model.EvaluacionCalidad;
import com.isw.compras_proveedores.model.Factura;
import com.isw.compras_proveedores.model.OrdenCompra;

public interface OrdenCompraService {
public void enviarOrdenPorCorreo(Long ordenCompraId);
public void aprobarOrdenPorCorreo(Long id);
public void rechazarOrdenPorCorreo(Long id);
public void notificarEnvioOrden(Long id);
public RecepcionDTO confirmarRecepcion(Long ordenId,EvaluacionCalidadDTO evaluacionCalidadDTO);
public Iterable<OrdenCompra> getOrdenes();
public Iterable<OrdenCompra> getOrdenesByEstado(String estado);
public Long countByStatus(String status);
public OrdenCompra getCompra(Long idCompra); 
}
