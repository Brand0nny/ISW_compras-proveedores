package com.isw.compras_proveedores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isw.compras_proveedores.model.DetalleRequisicion;
import com.isw.compras_proveedores.service.DetalleRequisicionService;

@RestController
@RequestMapping("/detallarRequisicion")
public class DetalleRequisicionController {
    @Autowired
    DetalleRequisicionService detalleRequisicionService;
    @PostMapping("/{id}")
    public ResponseEntity<DetalleRequisicion> detallarRequisicion(@PathVariable Long id, @RequestBody DetalleRequisicion detalleRequisicion){
        try {
            
            return ResponseEntity.ok().body(detalleRequisicionService.detallarRequisicion(id, detalleRequisicion));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/{id}")
     public ResponseEntity<DetalleRequisicion> requisicionDetallada(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(detalleRequisicionService.getRequisicionDetallada(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
     }
}
