package com.isw.compras_proveedores.service;

import com.isw.compras_proveedores.DTO.UsuarioDTO;
import com.isw.compras_proveedores.model.Usuario;

public interface UsuarioService {
    public Boolean registerUsuario(Usuario usuario);
    public Boolean deleteUsuario(Long usuarioId);
    public Iterable<UsuarioDTO> getAllUsuarios();
    public UsuarioDTO getUsuario(Long usuarioId);
    public Usuario logUsuario(String username, String password);
    public Boolean updateUsuario(Long id, UsuarioDTO usuarioDTO);
}
