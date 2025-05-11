package com.isw.compras_proveedores.service;

import java.util.List;

import com.isw.compras_proveedores.DTO.ResumenPresupuesto;
import com.isw.compras_proveedores.model.MovimientosPresupuesto;

public interface DashboardService {
  
  /** Resumen de presupuesto para un mes dado **/
  ResumenPresupuesto getResumenPresupuesto(int year, int month);
  
  /** Lista de movimientos del mes **/
  List<MovimientosPresupuesto> getMovimientos(int year, int month);

}
