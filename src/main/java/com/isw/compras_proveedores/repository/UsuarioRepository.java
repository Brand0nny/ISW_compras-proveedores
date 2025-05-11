package com.isw.compras_proveedores.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isw.compras_proveedores.model.Usuario;


@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    public Optional<Usuario> findByUsername(String username);

}
