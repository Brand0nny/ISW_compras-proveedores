package com.isw.compras_proveedores.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isw.compras_proveedores.model.DetalleRequisicion;
import com.isw.compras_proveedores.model.Requisicion;

@Repository
public interface RequisicionRepository extends CrudRepository<Requisicion, Long> {
    List<Requisicion> findByStatus(String status);

}
