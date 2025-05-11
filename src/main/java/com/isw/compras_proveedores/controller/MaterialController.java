package com.isw.compras_proveedores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isw.compras_proveedores.model.Material;
import com.isw.compras_proveedores.service.MaterialService;

@RestController
@RequestMapping("/materiales")
public class MaterialController {
@Autowired
MaterialService materialService;
@GetMapping("")
    public ResponseEntity<Iterable<Material>> getAllMateriales(){
        return ResponseEntity.ok(materialService.getAllMateriales());
    }
}
