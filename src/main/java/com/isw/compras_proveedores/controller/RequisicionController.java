package com.isw.compras_proveedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isw.compras_proveedores.Config.Session.UsuarioAutenticado;
import com.isw.compras_proveedores.model.OrdenCompra;
import com.isw.compras_proveedores.model.Requisicion;
import com.isw.compras_proveedores.model.Usuario;
import com.isw.compras_proveedores.service.RequisicionService;

@RestController
@RequestMapping("/requisicion")
public class RequisicionController {
    @Autowired
    RequisicionService requisicionService;

    @PostMapping("/create")
    public ResponseEntity<Requisicion> createRequisicion(@RequestBody Requisicion requisicion){
        try {
            
            
            return ResponseEntity.ok(requisicionService.registerRequisicion(requisicion));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<Requisicion>> getAllRequisiciones(){
        return ResponseEntity.ok().body(requisicionService.getRequisiciones());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Requisicion> getRequisicion(@PathVariable Long id){
        try{
            return ResponseEntity.ok().body(requisicionService.getRequisicion(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequisicion(@PathVariable Long id){
         try{
            requisicionService.deleteRequisicion(id);
            return ResponseEntity.ok().body("Eliminado correctamente");
         } catch (Exception e) {
            return ResponseEntity.badRequest().build();    
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> updateRequisicion(@PathVariable Long id, @RequestBody Requisicion requisicion){
        try {
            
            requisicionService.updateRequisicion(id, requisicion);
            return ResponseEntity.ok().body("Requsicion actualizada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<Iterable<Requisicion>> getRequisicionesPorEstado(@PathVariable String status){
        try{
            return ResponseEntity.ok().body(requisicionService.getRequisicionesPorEstado(status));
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PatchMapping("/{id}/aprobar")
    public ResponseEntity<OrdenCompra> aproveOrdenRequisicion(@PathVariable Long id, @RequestBody OrdenCompra ordenCompra){
       
        try{
            return ResponseEntity.ok().body(requisicionService.aproveOrdenRequisicion(id, ordenCompra.getProveedor().getProveedorId(), UsuarioAutenticado.get().getUsername()));
        }catch(Exception e){
            System.out.println(e.toString());
            
            return ResponseEntity.badRequest().build();
        }
    }
    @PatchMapping("/{id}/estado/{status}")
    public ResponseEntity<Requisicion> setStateOrdenRequisicion(@PathVariable Long id, @PathVariable String status){
        try{
            return ResponseEntity.ok().body(requisicionService.setStateOrdenRequisicion(id, status));
        }catch(Exception e){
            System.out.println(e.toString());
            
            return ResponseEntity.badRequest().build();
        }
    }

    
   @GetMapping("/ultimasrequired")
   public ResponseEntity<List<Requisicion>> getLatestRequiredRequisiciones(){
    return ResponseEntity.ok(requisicionService.getLatestRequiredRequisiciones());
   }

   @GetMapping("/ultimas5required")
   public ResponseEntity<List<Requisicion>> getTop5LatestRequiredRequisiciones(){
    return ResponseEntity.ok(requisicionService.getTop5LatestRequiredRequisiciones());
   }
   @GetMapping("/ultimasrequest")
   public ResponseEntity<List<Requisicion>> getLatestRequestRequisiciones(){
    return ResponseEntity.ok(requisicionService.getLatestRequestRequisiciones());
   }

   @GetMapping("/ultimas5request")
   public ResponseEntity<List<Requisicion>> getTop5LatestRequestRequisiciones(){
    return ResponseEntity.ok(requisicionService.getTop5LatestRequestRequisiciones());
   }
   @GetMapping("/count/{status}")
   public ResponseEntity<Long> countByStatus(@PathVariable String status){
    return ResponseEntity.ok(requisicionService.countByStatus(status));
   }

   @GetMapping("/desc")
   public ResponseEntity<List<Requisicion>> getRequisicionesDesc(){
    return ResponseEntity.ok(requisicionService.getRequisicionesDesc());

   }
   @GetMapping("/desc/{status}")
   public ResponseEntity<List<Requisicion>> getRequisicionesStatusDesc(@PathVariable String status){
    return ResponseEntity.ok(requisicionService.getRequisicionesStatusDesc(status));

   }
}
