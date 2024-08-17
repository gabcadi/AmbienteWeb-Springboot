package com.dcplinaWeb.controller;

import com.dcplinaWeb.domain.Clase;
import com.dcplinaWeb.domain.Reserva;
import com.dcplinaWeb.domain.Usuario;
import com.dcplinaWeb.services.ClaseService;
import com.dcplinaWeb.services.ReservaService;
import com.dcplinaWeb.services.UsuarioService;
import com.dcplinaWeb.servicesImpl.ReservaServiceImpl;
import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {

    @Autowired
    private ReservaServiceImpl reservaServiceImpl;

    @Autowired
    private ClaseService claseService;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario/home")
    public String usuarioHome() {
        return "usuario/home";
    }

    @GetMapping("/usuario/clase")
    public String mostrarClases(Model model) {
        model.addAttribute("clase", claseService.obtenerClasesDisponibles());
        return "usuario/clase";
    }

    @GetMapping("/usuario/mis-reservas")
    public String misReservas(Model model, Principal principal) {
        Usuario usuario = usuarioService.getUsuarioPorUsername(principal.getName());
        List<Reserva> reservas = reservaService.obtenerReservasUsuario(usuario.getIdUsuario());
        model.addAttribute("reservas", reservas);
        return "usuario/mis-reservas";
    }

    @PostMapping("/usuario/reservar")
    public String reservarClase(@RequestParam("idClase") Long idClase, Principal principal) {
        Usuario usuario = usuarioService.getUsuarioPorUsername(principal.getName());

        try {
            reservaService.crearReserva(usuario.getIdUsuario(), idClase);
            return "redirect:/usuario/mis-reservas";
        } catch (RuntimeException e) {
            return "redirect:/usuario/home?error=" + e.getMessage(); // Agregar mensaje de error a la URL
        }
    }

    @PostMapping("/usuario/cancelar-reserva/{id}")
    public String cancelarReserva(@PathVariable Long id) {
        System.out.println("Cancelando reserva con ID: " + id);
        reservaServiceImpl.cancelarReserva(id);
        return "redirect:/usuario/mis-reservas";
    }
}
