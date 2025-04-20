package com.isw.compras_proveedores.service;

import java.math.BigDecimal;

import com.isw.compras_proveedores.model.Pago;

public interface PagoService {
    public Pago pagarFactura(Long facturaId, BigDecimal paymentAmount);
    public void aprobarPago(Long id);
    public void rechazarPago(Long id);
}
