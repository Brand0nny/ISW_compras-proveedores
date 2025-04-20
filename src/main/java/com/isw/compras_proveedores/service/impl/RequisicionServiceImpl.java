package com.isw.compras_proveedores.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isw.compras_proveedores.model.DetalleOrdenCompra;
import com.isw.compras_proveedores.model.DetalleRequisicion;
import com.isw.compras_proveedores.model.OrdenCompra;
import com.isw.compras_proveedores.model.Proveedor;
import com.isw.compras_proveedores.model.Requisicion;
import com.isw.compras_proveedores.model.State;
import com.isw.compras_proveedores.repository.DetalleRequisicionRepository;
import com.isw.compras_proveedores.repository.OrdenCompraRepository;
import com.isw.compras_proveedores.repository.ProveedorRepository;
import com.isw.compras_proveedores.repository.RequisicionRepository;
import com.isw.compras_proveedores.service.RequisicionService;

@Service
public class RequisicionServiceImpl implements RequisicionService{
    @Autowired
    DetalleRequisicionRepository detalleRequisicionRepository;
    @Autowired
    OrdenCompraRepository ordenCompraRepository;
    @Autowired
    RequisicionRepository requisicionRepository;
    @Autowired
    ProveedorRepository proveedorRepository;

    @Override
    public Boolean registerRequisicion(Requisicion requisicion) {
        requisicion.setRequestDate(new Date());
        requisicion.setStatus(State.PENDIENTE.name());
        requisicionRepository.save(requisicion);
        return true;
    }

    @Override
    public Boolean deleteRequisicion(Long requisicionId) {
        Optional<Requisicion> requisicionOptional = requisicionRepository.findById(requisicionId);
        if(requisicionOptional.isPresent()){
            requisicionRepository.delete(requisicionOptional.get());
            return true;
        }
        return null;

    }

    @Override
    public Requisicion getRequisicion(Long requisicionId) {
        Optional<Requisicion> requisicionOptional = requisicionRepository.findById(requisicionId);
        if(requisicionOptional.isPresent()){
            return requisicionOptional.get();
        }
        throw new RuntimeException("No existe esa requisicion.");
    }

    @Override
    public Iterable<Requisicion> getRequisiciones() {
       return requisicionRepository.findAll();
    }

    @Override
    public Requisicion updateRequisicion(Long id, Requisicion requisicion) {
        Optional<Requisicion> requisicionOptional = requisicionRepository.findById(id);
        if(requisicionOptional.isPresent()){
            Requisicion viejo = requisicionOptional.get();
            viejo.setRequisicionId(requisicion.getRequisicionId());
            viejo.setCenterCost(requisicion.getCenterCost());
            viejo.setDescription(requisicion.getDescription());
            viejo.setMotive(requisicion.getMotive());
            viejo.setRequestDate(requisicion.getRequestDate());
            viejo.setRequiredDate(requisicion.getRequiredDate());
            
            return requisicionRepository.save(viejo);

        }
        else{
            throw new RuntimeException("No existe esa requisicion");
        }
    }

    
    @Override
    public Iterable<Requisicion> getRequisicionesPendientes() {
    return requisicionRepository.findByStatus(State.PENDIENTE.name());
}

    @Override
    public OrdenCompra aproveOrdenRequisicion(Long id, Long proveedor, String user) {
        Optional<Requisicion> requisicionOptional = requisicionRepository.findById(id);
        List<DetalleRequisicion> detalleRequisicionList = 
        detalleRequisicionRepository.findAllByRequisicion(requisicionOptional.get());
        if(requisicionOptional.isPresent()){
            StringBuilder sb = new StringBuilder();
            String s = proveedor + user + requisicionOptional.get().getCenterCost();
            OrdenCompra ordenGenerada = new OrdenCompra();
            ordenGenerada.setStatus(State.EN_PROCESO.name());
            ordenGenerada.setRequisicion(requisicionOptional.get());
            Proveedor prov = proveedorRepository.findById(proveedor)
            .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));
            ordenGenerada.setProveedor(prov);
            ordenGenerada.setOrderDate(new Date());
            ordenGenerada.setFactura(null);
            ordenGenerada.setOrderNumber(s);
            ordenGenerada.setAprovedBy(user);
            List<DetalleOrdenCompra> detalleOrden = new ArrayList<>();
            for(DetalleRequisicion dr : detalleRequisicionList){
                DetalleOrdenCompra detalleOrdenCompra = new DetalleOrdenCompra();
                detalleOrdenCompra.setUnitPrice(dr.getMaterial().getCost());
                detalleOrdenCompra.setQuantity(dr.getQuantity());
                detalleOrdenCompra.setMaterial(dr.getMaterial());
                detalleOrdenCompra.setOrdenCompra(ordenGenerada);
                detalleOrden.add(detalleOrdenCompra);
            }

            ordenGenerada.setDetalles(detalleOrden);

            return ordenCompraRepository.save(ordenGenerada);
         }
         return null;
        
    }

}
