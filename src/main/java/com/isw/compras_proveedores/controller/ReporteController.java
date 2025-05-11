package com.isw.compras_proveedores.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isw.compras_proveedores.model.Reporte;
import com.isw.compras_proveedores.service.ReporteService;

@RestController
@RequestMapping("/reporte")
public class ReporteController {
    @Autowired
    ReporteService reporteService;

    @GetMapping("/{reporteId}")
    public ResponseEntity<Reporte>  getReporte(@PathVariable Long reporteId){
        return ResponseEntity.ok(reporteService.getReporteById(reporteId));
    }
    @GetMapping("/")
    public ResponseEntity<Iterable<Reporte>>  getReportes(){
        return ResponseEntity.ok(reporteService.getReportes());
    }
}
