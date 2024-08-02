package com.dcplinaWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EntrenadorController {

    @GetMapping("/entrenador/home")
    public String entrenadorHome() {
        return "entrenador/home";
    }
    
    
    @GetMapping("/entrenador/inbox")
    public String entrenadorInbox() {
        return "entrenador/inbox";
    }
}

