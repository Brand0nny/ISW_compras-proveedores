package com.isw.compras_proveedores.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isw.compras_proveedores.DTO.MovimientoDTO;
import com.isw.compras_proveedores.DTO.ResumenPresupuesto;
import com.isw.compras_proveedores.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
@Autowired
  private DashboardService dashboardService;

  @GetMapping("/budget-summary/{year}-{month}")
  public ResumenPresupuesto getResumenPresupuesto(@PathVariable int year, @PathVariable int month) {
    return dashboardService.getResumenPresupuesto(year, month);
  }

@GetMapping("/movements/{year}-{month}")
public List<MovimientoDTO> getMovements(
             @PathVariable int year,
             @PathVariable int month) {
  return dashboardService
    .getMovimientos(year, month)
    .stream()
    .map(m -> new MovimientoDTO(
        m.getIdMovement(),
        m.getDescription(),
        BigDecimal.valueOf(m.getAmount()),
        m.getMovementDate()))
    .collect(Collectors.toList());
}

}
