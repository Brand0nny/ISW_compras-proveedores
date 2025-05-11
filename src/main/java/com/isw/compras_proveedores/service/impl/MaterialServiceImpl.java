package com.isw.compras_proveedores.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isw.compras_proveedores.model.Material;
import com.isw.compras_proveedores.repository.MaterialRepository;
import com.isw.compras_proveedores.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {
@Autowired
MaterialRepository materialRepository;
    @Override
    public Iterable<Material> getAllMateriales() {
        return materialRepository.findAll();
    }

}
