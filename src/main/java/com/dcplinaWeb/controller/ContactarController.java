package com.dcplinaWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactarController {
    @GetMapping("/contactar")
    public String contactar() {
        return "contactar";
    }
}
