
package com.dcplinaWeb.services;

import com.dcplinaWeb.domain.Usuario;
import java.util.List;


public interface UsuarioService {
    
    //Se obtiene una lista de usuarios en un list
    public List<Usuario> getUsuarios();
    
    //Se obtiene un Usuario a partir del id de un usuario
    public Usuario getUsuario(Usuario usuario);
    
    //Se obtiene un Usuario, a partir del username de un usuario
    public Usuario getUsuarioPorUsername(String username);
    
    //Se obtiene un Usuario a partir de username y el password de un usuario
    public Usuario getUsuarioPorUsernameYContrasena(String username, String contrasena);
    
    //Se obtiene un Usuario a partir del email y del username de un usuario
    public Usuario getUsuarioPorUsernameOCorreo(String username, String email);
    
    //Se valida si existe un Usuario considerando el username
    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo);
    
    //Se inserta un nuevo usuario si el id del usuario esta vacio
    //Se actualiza un usuario si el id del usuario NO esta vacio
    public void save(Usuario usuario, boolean crearRolUser);
    
    public Usuario getUsuarioPorId(Long idUsuario);
    
    //Se elimina el usuario que tiene el id pasado por parametro
    public void delete(Usuario usuario);
    
    public void promoteUserById(Long idUsuario);
    
    public void degradeUser(Long idUsuario);
    
}
