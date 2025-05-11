package com.isw.compras_proveedores.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.lang.Iterable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isw.compras_proveedores.DTO.EvaluacionCalidadDTO;
import com.isw.compras_proveedores.DTO.RecepcionDTO;
import com.isw.compras_proveedores.model.EvaluacionCalidad;
import com.isw.compras_proveedores.model.Factura;
import com.isw.compras_proveedores.model.OrdenCompra;
import com.isw.compras_proveedores.service.OrdenCompraService;
import com.isw.compras_proveedores.service.impl.EmailService;

@RestController
@RequestMapping("/ordenes")
public class OrdenCompraController {
   @Autowired
    OrdenCompraService ordenCompraService;

    @GetMapping("")
    public ResponseEntity<Iterable<OrdenCompra>> getOrdenes(){
        return ResponseEntity.ok(ordenCompraService.getOrdenes());
    }
    @GetMapping("/estado/{estado}")
    public ResponseEntity<Iterable<OrdenCompra>> getOrdenesByEstado(@PathVariable String estado){
        return ResponseEntity.ok(ordenCompraService.getOrdenesByEstado(estado));
    }
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
    @PostMapping("/recibir/{id}")
    public ResponseEntity<RecepcionDTO> recibirOrden(@PathVariable Long id, @RequestBody EvaluacionCalidadDTO evaluacionCalidadDTO){
        try {
            System.out.println("=========");
            return ResponseEntity.ok().body(ordenCompraService.confirmarRecepcion(id, evaluacionCalidadDTO));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    } 
    @GetMapping("/count/{status}")
    public ResponseEntity<Long> countByStatus(@PathVariable String status){
     return ResponseEntity.ok(ordenCompraService.countByStatus(status));
    }

    @GetMapping("/{idCompra}")
    public ResponseEntity<OrdenCompra> getCompra(@PathVariable Long idCompra){

        return ResponseEntity.ok(ordenCompraService.getCompra(idCompra));
    }
}
