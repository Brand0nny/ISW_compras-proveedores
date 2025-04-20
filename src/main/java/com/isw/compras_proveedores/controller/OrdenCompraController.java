package com.isw.compras_proveedores.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isw.compras_proveedores.model.Factura;
import com.isw.compras_proveedores.service.OrdenCompraService;
import com.isw.compras_proveedores.service.impl.EmailService;

@RestController
@RequestMapping("/ordenes")
public class OrdenCompraController {
   @Autowired
    OrdenCompraService ordenCompraService;

    @PostMapping("/enviarOrden/{id}")
    public ResponseEntity<String> enviarOrden(@PathVariable Long id) {
        try {
            ordenCompraService.enviarOrdenPorCorreo(id);
            return ResponseEntity.ok().body("Correo enviado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/aprobar/{id}")
    public ResponseEntity<String> aprobarOrden(@PathVariable Long id){
        try{
            ordenCompraService.aprobarOrdenPorCorreo(id);
            return ResponseEntity.ok().body("Gracias!");

        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/rechazar/{id}")
    public ResponseEntity<String> rechazarOrden(@PathVariable Long id){
        try{
            ordenCompraService.rechazarOrdenPorCorreo(id);
            return ResponseEntity.ok().body("ok!!!");
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/enviada/{id}")
    public ResponseEntity<String> notificarEnvioDeOrden(@PathVariable Long id){
        try {
            ordenCompraService.notificarEnvioOrden(id);
            return ResponseEntity.ok().body("ok!!!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/recibir/{id}")
    public ResponseEntity<Factura> recibirOrden(@PathVariable Long id){
        try {
            System.out.println("=========");
            return ResponseEntity.ok().body(ordenCompraService.confirmarRecepcion(id));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    } 
}
