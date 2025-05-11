package com.isw.compras_proveedores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.isw.compras_proveedores.model.Factura;;
@Repository
public interface FacturaRepository extends CrudRepository<Factura, Long> {
    @Query("SELECT DISTINCT f FROM Factura f "
        + " LEFT JOIN FETCH f.pago p "
        + " LEFT JOIN FETCH f.ordenCompra o")
    Iterable<Factura> findAllWithRelations();

}
