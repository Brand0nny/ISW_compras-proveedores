package com.isw.compras_proveedores.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isw.compras_proveedores.model.OrdenCompra;

@Repository
public interface OrdenCompraRepository extends CrudRepository<OrdenCompra,Long>{

}
