package com.isw.compras_proveedores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isw.compras_proveedores.service.PagoService;

@RestController
@RequestMapping("/finanzas")
public class FinanzasController {
@Autowired
PagoService pagoService;

    @GetMapping("/aprobar/{id}")
    public ResponseEntity<String> aprobarPago(@PathVariable Long id){
        try {
            pagoService.aprobarPago(id);
            return ResponseEntity.ok().body("aprobado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    } 
}
