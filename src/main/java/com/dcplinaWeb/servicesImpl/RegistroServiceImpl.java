package com.dcplinaWeb.servicesImpl;

import com.dcplinaWeb.domain.Usuario;
import com.dcplinaWeb.services.CorreoService;
import com.dcplinaWeb.services.RegistroService;
import com.dcplinaWeb.services.UsuarioService;
import jakarta.mail.MessagingException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RegistroServiceImpl implements RegistroService {

    @Autowired
    private CorreoService correoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MessageSource messageSource;

    @Override
    public Model activar(Model model, String username, String clave) {
        Usuario usuario
                = usuarioService.getUsuarioPorUsernameYContrasena(username,
                        clave);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
        } else {
            model.addAttribute("titulo", messageSource.getMessage(
                    "registro.activar",
                    null, Locale.getDefault()));
            model.addAttribute("mensaje", messageSource.getMessage("registro.activar.error", null, Locale.getDefault()));

        }
        return model;

    }

    @Override
    public void activar(Usuario usuario, MultipartFile imagenFile) {
        var codigo = new BCryptPasswordEncoder();
        usuario.setContrasena(codigo.encode(usuario.getContrasena()));
    }

    @Override
    public Model crearUsuario(Model model, Usuario usuario) throws MessagingException {
        String mensaje;
        if (!usuarioService.existeUsuarioPorUsernameOCorreo(usuario.getUsername(), usuario.getEmail())) {
            String clave = demeClave();
            usuario.setContrasena(clave);
            usuario.setActivo(false);
            usuarioService.save(usuario, true);
            enviarCorreoActivar(usuario, clave);
            mensaje = String.format(
                    messageSource.getMessage("registro.mensaje.activacion.ok",
                            null, Locale.getDefault()), usuario.getEmail());
        } else {
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.usuario.o.correo",
                            null, Locale.getDefault()),
                    usuario.getUsername(),
                    usuario.getEmail());
        }
        model.addAttribute("titulo",
                messageSource.getMessage(
                        "registro.activar", null,
                        Locale.getDefault()));
        model.addAttribute("mensaje",
                mensaje);
        return model;
    }

   
    @Override
    public Model recordarUsuario(Model model, Usuario usuario){

        String mensaje;
        Usuario usuario2 = usuarioService.getUsuarioPorUsernameOCorreo(usuario.getUsername(), usuario.getEmail());

        if (usuario2 != null) {
            String clave = demeClave();
            usuario2.setContrasena(clave);
            usuario2.setActivo(false);
            usuarioService.save(usuario2, false);
            try {
                enviarCorreoRecordar(usuario2, clave);
            } catch (MessagingException ex) {
                Logger.getLogger(RegistroServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            mensaje = String.format(messageSource.getMessage(
                    "registro.mensaje.recordar.ok",
                    null,
                    Locale.getDefault()));
        } else {
            mensaje = String.format(messageSource.getMessage(
                    "registro.mensaje.usuario.o.correo",
                    null,
                    Locale.getDefault()));
        }

        model.addAttribute(
                "titulo",
                messageSource.getMessage(
                        "registro.activar",
                        null,
                        Locale.getDefault())
        );
        model.addAttribute("mensaje", mensaje);
        return model;
    }

    private String demeClave() {
        String tira = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_*+-";
        String clave = "";
        for (int i = 0; i < 40; i++) {
            clave += tira.charAt((int) (Math.random() * tira.length()));
        }
        return clave;
    }

    @Value("${servidor.http}")
    private String servidor;

    private void enviarCorreoActivar(Usuario usuario, String clave) throws MessagingException {
        String mensaje = messageSource.getMessage(
                "registro.correo.activar",
                null,
                Locale.getDefault());
        mensaje = String.format(mensaje, usuario.getNombre(),
                usuario.getApellido(), servidor,
                usuario.getUsername(), clave);
        String asunto = messageSource.getMessage(
                "regostro.mensaje.activacion",
                null,
                Locale.getDefault());
        correoService.enviarCorreoHtml(
                usuario.getEmail(),
                asunto, 
                mensaje);
    }
    
    private void enviarCorreoRecordar(Usuario usuario, String clave) throws MessagingException {
        String mensaje = messageSource.getMessage(
                "registro.correo.recordar",
                null,
                Locale.getDefault());
        mensaje = String.format(mensaje, usuario.getNombre(),
                usuario.getApellido(), servidor,
                usuario.getUsername(), clave);
        String asunto = messageSource.getMessage(
                "regostro.mensaje.recordar",
                null,
                Locale.getDefault());
        correoService.enviarCorreoHtml(
                usuario.getEmail(),
                asunto, 
                mensaje);
    }

}
