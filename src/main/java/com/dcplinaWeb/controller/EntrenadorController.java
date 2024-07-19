package com.dcplinaWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntrenadorController {

    @GetMapping("/entrenador/home")
    public String entrenadorHome() {
        return "entrenador/home";
    }
}