package com.isw.compras_proveedores.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isw.compras_proveedores.Config.Session.UsuarioAutenticado;
import com.isw.compras_proveedores.model.Proveedor;
import com.isw.compras_proveedores.service.ProveedorService;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
    @Autowired
    ProveedorService proveedorService;

    @PostMapping("/register")
    public ResponseEntity<String> registerProveedor(@RequestBody Proveedor proveedor){
        UsuarioAutenticado.validarRol("ADMIN_PROVEEDORES","ADMIN");
        try{
            proveedorService.registerProveedor(proveedor);
            return ResponseEntity.ok().body("Registrado con éxito");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedor(@PathVariable Long id){
        UsuarioAutenticado.validarRol("ADMIN_PROVEEDORES","ADMIN");
        try{
            return ResponseEntity.ok().body(proveedorService.getProveedor(id));
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/")
        public ResponseEntity<Iterable<Proveedor>> getAllProveedores(){
            UsuarioAutenticado.validarRol("ADMIN_PROVEEDORES","ADMIN");
            try{
                return ResponseEntity.ok().body(proveedorService.getProveedores());
            }catch(Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
    
    @PutMapping("{id}")
    public ResponseEntity<Proveedor> updateProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor){
        UsuarioAutenticado.validarRol("ADMIN_PROVEEDORES","ADMIN");
        try{
            return ResponseEntity.ok().body(proveedorService.updateProveedor(id, proveedor));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("/categorias")
    public ResponseEntity<List<String>> getCategorias() {
        return ResponseEntity.ok(proveedorService.getCategoriasDistintas());
    }
    @GetMapping("/por-categoria/{category}")
    public ResponseEntity<List<Proveedor>> getProveedoresByCategory(@PathVariable String category){
        return ResponseEntity.ok(proveedorService.getProveedoresByCategory(category));
    }
    @GetMapping("/top")
    public ResponseEntity<List<Proveedor>> getProveedoresTop(){
        return ResponseEntity.ok(proveedorService.getTopProveedores());
    }
}
