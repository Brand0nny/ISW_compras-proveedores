package com.isw.compras_proveedores.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isw.compras_proveedores.model.DetalleRequisicion;
import com.isw.compras_proveedores.model.Requisicion;
import com.isw.compras_proveedores.repository.DetalleRequisicionRepository;
import com.isw.compras_proveedores.repository.RequisicionRepository;
import com.isw.compras_proveedores.service.DetalleRequisicionService;

@Service
public class DetalleRequisicionServiceImpl implements DetalleRequisicionService{
    @Autowired
    DetalleRequisicionRepository detalleRequisicionRepository;
    @Autowired
    RequisicionRepository requisicionRepository;
    @Override
    public DetalleRequisicion detallarRequisicion(Long id, DetalleRequisicion detalleRequisicion) {
        Optional<Requisicion> requisicionOptional = requisicionRepository.findById(id);
        if(requisicionOptional.isPresent()){
            detalleRequisicion.setRequisicion(requisicionOptional.get());
            return detalleRequisicionRepository.save(detalleRequisicion);
        }  
        return null;
    }
    @Override
    public DetalleRequisicion getRequisicionDetallada(Long id) {
        Optional<DetalleRequisicion> detalleRequisicionOptional = detalleRequisicionRepository.findById(id);
        if(detalleRequisicionOptional.isPresent()){
            return detalleRequisicionOptional.get();
        }
        return null;
    }

}
