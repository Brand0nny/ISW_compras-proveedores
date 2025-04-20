package com.isw.compras_proveedores.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isw.compras_proveedores.model.DetalleRequisicion;
import com.isw.compras_proveedores.model.Requisicion;

@Repository
public interface DetalleRequisicionRepository extends CrudRepository<DetalleRequisicion, Long> {
        public Optional<DetalleRequisicion> findByRequisicion(Requisicion requisicion);
        public List<DetalleRequisicion> findAllByRequisicion(Requisicion requisicion);

}
