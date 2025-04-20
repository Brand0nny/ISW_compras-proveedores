package com.isw.compras_proveedores.service;

import com.isw.compras_proveedores.model.OrdenCompra;
import com.isw.compras_proveedores.model.Requisicion;

public interface RequisicionService {
public Boolean registerRequisicion(Requisicion requisicion);
    public Boolean deleteRequisicion(Long requisicionId);
    public Requisicion getRequisicion(Long requisicionId);
    public Iterable<Requisicion> getRequisiciones();
    public Requisicion updateRequisicion(Long id, Requisicion requisicion);
    public Iterable<Requisicion> getRequisicionesPendientes();
    public OrdenCompra aproveOrdenRequisicion(Long id, Long proveedor, String user);
}
