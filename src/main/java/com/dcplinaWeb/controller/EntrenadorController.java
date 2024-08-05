package com.dcplinaWeb.controller;

import com.dcplinaWeb.domain.Usuario;
import com.dcplinaWeb.services.UsuarioService;
import com.dcplinaWeb.servicesImpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/entrenador")
public class EntrenadorController {

    @Autowired
    UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/home")
    public String entrenadorHome() {
        return "entrenador/home";
    }

    @GetMapping("/inbox")
    public String entrenadorInbox() {
        return "entrenador/inbox";
    }

    @GetMapping("/usuarios")
    public String inicio(Model model) {
        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("totalUsuarios", usuarios.size());
        return "entrenador/usuarios";
    }

    @GetMapping("/nuevo")
    public String usuarioNuevo(Usuario usuario) {
        return "/entrenador/guardar";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam int telefono,
            @RequestParam String username,
            @RequestParam String email) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setTelefono(telefono);
        usuario.setUsername(username);
        usuario.setEmail(email);

        usuarioService.save(usuario, true);

        return "redirect:/entrenador/home";
    }

    @GetMapping("/usuarios/eliminar/{idUsuario}")
    public String usuarioEliminar(Usuario usuario) {
        usuarioService.delete(usuario);
        return "redirect:/entrenador/usuarios";
    }

    @GetMapping("/usuarios/editar/{idUsuario}")
    public String usuarioEditar(Usuario usuario, Model model) {

        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);

        return "redirect:/entrenador/usuarios";
    }

    @GetMapping("/usuarios/inactivar/{idUsuario}")
    public String inactivarUsuario(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioServiceImpl.getUsuarioPorId(idUsuario);
        if (usuario != null) {
            usuarioServiceImpl.inactivarUsuario(usuario);
        }
        return "redirect:/entrenador/usuarios";
    }

    @GetMapping("/usuarios/activar/{idUsuario}")
    public String activarUsuario(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioServiceImpl.getUsuarioPorId(idUsuario);
        if (usuario != null) {
            usuarioServiceImpl.activarUsuario(usuario);
        }
        return "redirect:/entrenador/usuarios";
    }
}
