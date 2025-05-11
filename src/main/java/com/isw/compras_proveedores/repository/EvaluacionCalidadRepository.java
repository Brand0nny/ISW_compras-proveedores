package com.isw.compras_proveedores.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.core.CrudMethods;
import org.springframework.stereotype.Repository;

import com.isw.compras_proveedores.model.EvaluacionCalidad;
import com.isw.compras_proveedores.model.Proveedor;

@Repository
public interface EvaluacionCalidadRepository extends CrudRepository<EvaluacionCalidad, Long> {
    List<EvaluacionCalidad> findByProveedor(Proveedor proveedor);
}
