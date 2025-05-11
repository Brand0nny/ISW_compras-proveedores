package com.isw.compras_proveedores.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isw.compras_proveedores.model.Material;

@Repository
public interface MaterialRepository extends CrudRepository<Material, Long> {

}
