package com.isw.compras_proveedores.service;

import com.isw.compras_proveedores.model.Reporte;

public interface ReporteService {
    public Reporte getReporteById(Long reporteId);
    public Iterable<Reporte> getReportes();
}
