package com.isw.compras_proveedores.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isw.compras_proveedores.model.Proveedor;
import com.isw.compras_proveedores.repository.ProveedorRepository;
import com.isw.compras_proveedores.service.ProveedorService;

@Service
public class ProveedorServiceImpl implements ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;
    @Override
    public Boolean registerProveedor(Proveedor proveedor) {
        Optional<Proveedor> optionalProveedor = proveedorRepository.findByName(proveedor.getName());
        if(!optionalProveedor.isPresent()){
            proveedorRepository.save(proveedor);
            return true;
        }

        return null;
    }
//pendiente 
    @Override
    public Boolean deleteProveedor(Long proveedorId) {

        throw new UnsupportedOperationException("Unimplemented method 'deleteProveedor'");
    }

    @Override
    public Proveedor getProveedor(Long proveedorId) {
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(proveedorId);

        if(proveedorOptional.isPresent()){
            return proveedorOptional.get();
        }
        return null;
    }

    @Override
    public Iterable<Proveedor> getProveedores() {
        return proveedorRepository.findAll();
    }
    @Override
    public Proveedor updateProveedor(Long id, Proveedor nuevoProveedor) {
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if(proveedorOptional.isPresent()){
            Proveedor viejo = proveedorOptional.get();
            viejo.setName(nuevoProveedor.getName());
            viejo.setContactInfo(nuevoProveedor.getContactInfo());
            viejo.setEvaluaciones(nuevoProveedor.getEvaluaciones());
            viejo.setEvaluationScore(nuevoProveedor.getEvaluationScore());
            viejo.setEmail(nuevoProveedor.getEmail());
            viejo.setPaymentMethod(nuevoProveedor.getPaymentMethod());
            viejo.setAccountHolder(nuevoProveedor.getAccountHolder());
            viejo.setAccountNumber(nuevoProveedor.getAccountNumber());
            viejo.setAddress(nuevoProveedor.getAddress());
            viejo.setBankName(nuevoProveedor.getBankName());
            viejo.setCity(nuevoProveedor.getCity());
            viejo.setClabe(nuevoProveedor.getClabe());
            viejo.setCountry(nuevoProveedor.getCountry());
            viejo.setCategory(nuevoProveedor.getCategory());
            return proveedorRepository.save(viejo);
        } else {
            throw new RuntimeException("Proveedor no encontrado");
        }
}
    @Override
    public List<Proveedor> getProveedoresByCategory(String category) {
        return proveedorRepository.findByCategory(category);
    }
    @Override
    public List<Proveedor> getTopProveedores() {
        return proveedorRepository.findAllByOrderByEvaluationScoreDesc();
    }
    @Override
    public List<String> getCategoriasDistintas(){
        return proveedorRepository.findDistinctCategory();
    }
}