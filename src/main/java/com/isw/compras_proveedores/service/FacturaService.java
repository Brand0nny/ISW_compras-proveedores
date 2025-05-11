package com.isw.compras_proveedores.service;

import org.springframework.stereotype.Service;

import com.isw.compras_proveedores.model.Factura;

public interface FacturaService  {
    public Iterable<Factura> getFacturas();
    public Factura getFactura(Long id);
}
