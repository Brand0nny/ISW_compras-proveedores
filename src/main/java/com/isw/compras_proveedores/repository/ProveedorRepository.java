package com.isw.compras_proveedores.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isw.compras_proveedores.model.Proveedor;

@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor, Long> {
    public Optional<Proveedor> findByName(String name);
    public List<Proveedor> findByCategory(String category);
    List<Proveedor> findAllByOrderByEvaluationScoreDesc();
    @Query("SELECT DISTINCT p.category FROM Proveedor p")
    List<String> findDistinctCategory();

}
