package com.dcplinaWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PreguntasFrecuentesController {
    @GetMapping("/faq")
    public String preguntasfrecuentes() {
        return "preguntasFrecuentes";
    }
}
