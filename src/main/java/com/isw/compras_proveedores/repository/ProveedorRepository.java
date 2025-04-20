package com.isw.compras_proveedores.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isw.compras_proveedores.model.Proveedor;

@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor, Long> {
    public Optional<Proveedor> findByName(String name);

}
