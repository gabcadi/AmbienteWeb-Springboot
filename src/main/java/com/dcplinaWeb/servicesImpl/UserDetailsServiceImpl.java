
package com.dcplinaWeb.servicesImpl;

import com.dcplinaWeb.dao.UsuarioDao;
import com.dcplinaWeb.domain.Rol;
import com.dcplinaWeb.domain.Usuario;
import com.dcplinaWeb.services.UsuarioDetailsService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userDetailsService")
public class UserDetailsServiceImpl
        implements UsuarioDetailsService, UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private HttpSession session;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        
        //Se busca el usuario en la tabla usuarios por medio del username
        Usuario usuario = usuarioDao.findByUsername(username);
        
        //Se valida si se encontro el usuario con el username pasado
        if(usuario == null || !usuario.isActivo()){
            //El usuario no se encontro
            throw new UsernameNotFoundException(username);
        }
       
        
        //Se deben recuperar los roles del usuario y crear un ArrayList con Roles
        var roles = new ArrayList<GrantedAuthority>();
        
        //Se revisan los roles del usuario y se convierten en roles de seguridad
        for(Rol r: usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(r.getNombre()));
        }
        return new User(usuario.getUsername(),usuario.getContrasena(),roles);
    }
}
