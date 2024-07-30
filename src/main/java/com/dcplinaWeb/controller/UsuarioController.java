
package com.dcplinaWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {
    
    @GetMapping("/usuario/home")
    public String entrenadorHome() {
        return "usuario/home";
    }
    
}
