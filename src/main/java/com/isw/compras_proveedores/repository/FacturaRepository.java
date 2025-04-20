package com.isw.compras_proveedores.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.isw.compras_proveedores.model.Factura;;
@Repository
public interface FacturaRepository extends CrudRepository<Factura, Long> {

}
