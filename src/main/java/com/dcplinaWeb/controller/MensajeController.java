package com.dcplinaWeb.controller;

import com.dcplinaWeb.domain.Mensaje;
import com.dcplinaWeb.services.MensajeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    // Muestra la página de contacto con el formulario
    @GetMapping("/contactar")
    public String mostrarFormularioContacto() {
        return "contactar";
    }

    @PostMapping("/enviarMensaje")
    public String enviarMensaje(
            @RequestParam("nombre") String nombre,
            @RequestParam("email") String email,
            @RequestParam("asunto") String asunto,
            @RequestParam("mensaje") String contenidoMensaje,
            Model model) {

        // Crear y guardar el mensaje
        Mensaje mensaje = new Mensaje();
        mensaje.setNombre(nombre);
        mensaje.setEmail(email);
        mensaje.setAsunto(asunto);
        mensaje.setContenido(contenidoMensaje);
        mensajeService.guardarMensaje(mensaje);

        // Añadir un mensaje de éxito a la vista
        model.addAttribute("mensajeExito", "Tu mensaje ha sido enviado exitosamente! Pronto el coach estará en contacto contigo :)");
        return "contactar";
    }

    // Muestra todos los mensajes en la vista del inbox del administrador
    @GetMapping("/admin/mensajes")
    public String verMensajes(Model model) {
        List<Mensaje> mensajes = mensajeService.obtenerTodosLosMensajes();
        mensajes.forEach(m -> System.out.println(m)); 
        model.addAttribute("mensajes", mensajes);
        return "admin/mensajes";
    }

    // Muestra los detalles de un mensaje específico por su ID
    @GetMapping("/admin/mensajes/{id}")
    public String verDetalleMensaje(@PathVariable("id") Long id, Model model) {
        Mensaje mensaje = mensajeService.obtenerMensajePorId(id);
        if (mensaje != null) {
            model.addAttribute("mensaje", mensaje);
            return "admin/mensaje-detalle";
        } else {
            return "redirect:/admin/mensajes";
        }
    }
}
