package com.isw.compras_proveedores.controller;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isw.compras_proveedores.model.Pago;
import com.isw.compras_proveedores.service.PagoService;

@RestController
@RequestMapping("/pagos")
public class PagoController {
@Autowired 
PagoService pagoService;
    @PostMapping("/pagar/{id}")
    public ResponseEntity<Pago> realizarPago(@PathVariable Long id, @RequestBody Pago paymentAmount){
        try {
            BigDecimal pago = paymentAmount.getPaymentAmount();
            System.out.println(pago);
            return ResponseEntity.ok(pagoService.pagarFactura(id, pago));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
