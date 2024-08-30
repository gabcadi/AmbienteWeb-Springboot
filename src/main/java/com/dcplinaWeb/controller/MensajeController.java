package com.dcplinaWeb.controller;

import com.dcplinaWeb.domain.Mensaje;
import com.dcplinaWeb.servicesImpl.MensajeServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mensajes")
public class MensajeController {
    @Autowired
    private MensajeServiceImpl mensajeService;

    @GetMapping("/nuevo")
    public String nuevoMensaje(Model model) {
        model.addAttribute("mensaje", new Mensaje());
        return "mensajes/nuevo";
    }

    @PostMapping("/enviar")
    public String enviarMensaje(@ModelAttribute Mensaje mensaje, RedirectAttributes redirectAttributes) {
        mensajeService.enviarMensaje(mensaje.getNombreUsuario(), mensaje.getEmail(), mensaje.getAsunto(), mensaje.getContenido());
        redirectAttributes.addFlashAttribute("mensajeExito", "Mensaje enviado con éxito");
        return "redirect:/";
    }

    @GetMapping("/admin-inbox")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String verInboxAdmin(Model model) {
        List<Mensaje> mensajes = mensajeService.obtenerTodosMensajes();
        model.addAttribute("mensajes", mensajes);
        return "mensajes/admin-inbox";
    }

    @PostMapping("/marcar-leido/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String marcarComoLeido(@PathVariable Long id) {
        mensajeService.marcarComoLeido(id);
        return "redirect:/mensajes/admin-inbox";
    }

    @PostMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String eliminarMensaje(@PathVariable Long id) {
        mensajeService.eliminarMensaje(id);
        return "redirect:/mensajes/admin-inbox";
    }
}
