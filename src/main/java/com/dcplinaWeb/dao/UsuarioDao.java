package com.dcplinaWeb.dao;

import com.dcplinaWeb.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao
        extends JpaRepository<Usuario, Long> {

    
    Usuario findByUsername(String username);
        
    Usuario findByUsernameAndContrasena(String username, String contrasena);
    
    Usuario findByUsernameOrEmail(String username, String email);
    
    boolean existsByUsernameOrEmail(String username, String email);
    
}
