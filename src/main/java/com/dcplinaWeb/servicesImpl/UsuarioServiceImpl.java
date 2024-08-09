package com.dcplinaWeb.servicesImpl;

import com.dcplinaWeb.dao.RolDao;
import com.dcplinaWeb.dao.UsuarioDao;
import com.dcplinaWeb.domain.Rol;
import com.dcplinaWeb.domain.Usuario;
import com.dcplinaWeb.services.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private RolDao rolDao;

    @Transactional
    public void inactivarUsuario(Usuario usuario) {
        usuario.setActivo(false);
        usuarioDao.save(usuario);
    }

    @Transactional
    public void activarUsuario(Usuario usuario) {
        usuario.setActivo(true);
        usuarioDao.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameYContrasena(String username, String contrasena) {
        return usuarioDao.findByUsernameAndContrasena(username, contrasena);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameOCorreo(String username, String email) {
        return usuarioDao.findByUsernameOrEmail(username, email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo) {
        return usuarioDao.existsByUsernameOrEmail(username, correo);
    }

    @Override
    @Transactional
    public void save(Usuario usuario, boolean crearRolUser) {
        usuario = usuarioDao.save(usuario);
        if (crearRolUser) {
            Rol rol = new Rol();
            rol.setNombre("ROLE_INVITADO");
            rol.setId_usuario(usuario.getIdUsuario());
            rolDao.save(rol);

        }
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    public Usuario getUsuarioPorId(Long idUsuario) {
        return usuarioDao.findById(idUsuario).orElse(null);

    }

    @Override
    @Transactional
    public void promoteUserById(Long idUsuario) {
        Usuario usuario = usuarioDao.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rolInvitado = rolDao.findByNombreAndIdUsuario("ROLE_INVITADO", usuario.getIdUsuario());
        if (rolInvitado == null) {
            throw new RuntimeException("El usuario no tiene rol de invitado");
        }

        rolInvitado.setNombre("ROLE_USER");
        rolDao.save(rolInvitado);
    }

    @Override
    @Transactional
    public void degradeUser(Long idUsuario) {
        Usuario usuario = usuarioDao.findById(idUsuario).orElse(null);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        Rol rolUsuario = rolDao.findByNombreAndIdUsuario("ROLE_USER", idUsuario);
        if (rolUsuario == null) {
            throw new RuntimeException("El usuario no tiene rol de usuario");
        }
        rolUsuario.setNombre("ROLE_INVITADO");
        rolDao.save(rolUsuario);
    }

//    @Transactional
//    public void activarUsuario(Usuario usuario, boolean Activo) {
//        usuario.setActivo(true);
//        usuarioDao.save(usuario);
//    }
}
