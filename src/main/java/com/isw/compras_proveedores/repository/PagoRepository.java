package com.isw.compras_proveedores.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isw.compras_proveedores.model.Factura;
import com.isw.compras_proveedores.model.Pago;

@Repository
public interface PagoRepository extends CrudRepository<Pago, Long> {
    Optional<Pago> findByFactura(Factura factura);

}
