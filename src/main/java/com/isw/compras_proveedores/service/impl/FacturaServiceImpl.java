package com.isw.compras_proveedores.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isw.compras_proveedores.model.Factura;
import com.isw.compras_proveedores.repository.FacturaRepository;
import com.isw.compras_proveedores.service.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService{
@Autowired
FacturaRepository facturaRepository;
    @Override
    public Iterable<Factura> getFacturas() {
       return facturaRepository.findAllWithRelations();
    }
    @Override
    public Factura getFactura(Long id) {
        return facturaRepository.findById(id).get();
   }

}
