package com.isw.compras_proveedores.DTO;

import com.isw.compras_proveedores.model.Factura;
import com.isw.compras_proveedores.model.Reporte;

public class RecepcionDTO {
private Factura factura;
  private Reporte reporte;
  
  public Factura getFactura() {
    return factura;
  }
  public void setFactura(Factura factura) {
    this.factura = factura;
  }
  public Reporte getReporte() {
    return reporte;
  }
  public void setReporte(Reporte reporte) {
    this.reporte = reporte;
  }
}
