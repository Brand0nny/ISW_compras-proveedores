package com.isw.compras_proveedores.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isw.compras_proveedores.model.Factura;
import com.isw.compras_proveedores.service.FacturaService;

@RestController
@RequestMapping("/factura")
public class FacturaController {
@Autowired
FacturaService facturaService;
@GetMapping("/")
public ResponseEntity<Iterable<Factura>> getFacturas(){
    return ResponseEntity.ok(facturaService.getFacturas());
}
@GetMapping("/{id}")
public ResponseEntity<Factura> getFactu(Long id){
    return ResponseEntity.ok(facturaService.getFactura(id));
}
}
