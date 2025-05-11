package com.isw.compras_proveedores.service;

import com.isw.compras_proveedores.model.DetalleRequisicion;

public interface DetalleRequisicionService {
    public DetalleRequisicion detallarRequisicion(Long id, DetalleRequisicion detalleRequisicion);
    public DetalleRequisicion getRequisicionDetallada(Long id);

}
