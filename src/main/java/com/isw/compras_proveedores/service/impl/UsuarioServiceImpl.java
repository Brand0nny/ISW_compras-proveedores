package com.isw.compras_proveedores.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isw.compras_proveedores.DTO.UsuarioDTO;
import com.isw.compras_proveedores.model.Usuario;
import com.isw.compras_proveedores.model.Usuario.Role;
import com.isw.compras_proveedores.service.UsuarioService;
import com.isw.compras_proveedores.repository.UsuarioRepository;
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Boolean registerUsuario(Usuario usuario) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(usuario.getUsername());
    
        if (usuarioOptional.isPresent()) {
            Usuario existingUsuario = usuarioOptional.get();
            if (existingUsuario.getUsername().equals(usuario.getUsername())) {
                return false; 
            }
        }
        String hashedPw = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
        Usuario usuarioCreado = new Usuario();
        usuarioCreado.setUsername(usuario.getUsername());
        usuarioCreado.setEmail(usuario.getEmail());
        usuarioCreado.setPassword(hashedPw);
        usuarioCreado.setRole(usuario.getRole());
        usuarioRepository.save(usuarioCreado);
        return true; 
    }

    @Override
    public Boolean deleteUsuario(Long usuarioId) {
        if (usuarioId == null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo");
        }
        usuarioRepository.deleteById(usuarioId);
        return true;
    }

    @Override
    public Iterable<UsuarioDTO> getAllUsuarios() {
    Iterable<UsuarioDTO> usuarios = StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
            .map(usuario -> {
              UsuarioDTO usuarioDTO = new UsuarioDTO();
              usuarioDTO.setUsuarioId(usuario.getUsuarioId());
              usuarioDTO.setUsername(usuario.getUsername());
              usuarioDTO.setEmail(usuario.getEmail());
              usuarioDTO.setRole(usuario.getRole());
              return usuarioDTO;
            }).toList();
               
        return usuarios;
    }

    @Override
    public UsuarioDTO getUsuario(Long usuarioId) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setUsuarioId(usuario.getUsuarioId());
            usuarioDTO.setUsername(usuario.getUsername());
            usuarioDTO.setEmail(usuario.getEmail());
            usuarioDTO.setRole(usuario.getRole());
            return usuarioDTO;
        }
        return null;
    }

    @Override
    public Usuario logUsuario(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);
        System.out.println(usuarioOptional);
        System.out.println(password);
        System.out.println(usuarioOptional.get().getPassword());
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            if (usuario.getPassword() == null || !usuario.getPassword().startsWith("$2a$")) {
                throw new RuntimeException("Formato de contraseña inválido");
            }
            if(BCrypt.checkpw(password, usuario.getPassword())){
                return usuario;
            }
        }
        throw new RuntimeException("Credenciales invalidas");
    }

    @Override
    public Boolean updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioDTO.getUsuarioId());
        if(usuarioOptional.isPresent()){
            String hashedPw = BCrypt.hashpw(usuarioDTO.getPassword(), BCrypt.gensalt());
            Usuario viejo = usuarioOptional.get();
            viejo.setUsuarioId(usuarioDTO.getUsuarioId());
            viejo.setUsername(usuarioDTO.getUsername());
            viejo.setEmail(usuarioDTO.getEmail());
            viejo.setPassword(hashedPw);
            viejo.setRole(usuarioDTO.getRole());
            usuarioRepository.save(viejo);
            return true;
        }

        throw new RuntimeException("Usuario no encontrado");
    }


}
