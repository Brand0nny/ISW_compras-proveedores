package com.isw.compras_proveedores.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isw.compras_proveedores.model.PresupuestoCompras;

@Repository
public interface PresupuestoComprasRepository extends CrudRepository<PresupuestoCompras, Long> {
    Optional<PresupuestoCompras> findByYearAndMonth(int year, int month);
}
