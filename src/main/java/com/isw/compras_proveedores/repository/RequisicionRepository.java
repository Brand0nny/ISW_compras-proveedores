package com.isw.compras_proveedores.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isw.compras_proveedores.model.DetalleRequisicion;
import com.isw.compras_proveedores.model.Requisicion;

@Repository
public interface RequisicionRepository extends CrudRepository<Requisicion, Long> {
    List<Requisicion> findByStatus(String status);
    List<Requisicion> findAllByOrderByRequestDateDesc();
    List<Requisicion> findTop5ByOrderByRequestDateDesc();
    List<Requisicion> findByStatusOrderByRequisicionIdDesc(String status);
    List<Requisicion> findAllByOrderByRequiredDateDesc();
    List<Requisicion> findTop5ByOrderByRequiredDateDesc();
    List<Requisicion> findAllByOrderByRequisicionIdDesc();
    Long countByStatus(String status);
}
