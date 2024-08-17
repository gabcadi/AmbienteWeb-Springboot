package com.dcplinaWeb.components;

import com.dcplinaWeb.servicesImpl.ClaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClaseCleaner {

    @Autowired
    private ClaseServiceImpl claseServiceImpl;

    // Ejecuta el método cada hora
    @Scheduled(cron = "0 0 * * * ?")
    public void limpiarClasesAntiguas() {
        System.out.println("Limpiando clases antiguas...");
        claseServiceImpl.eliminarClasesExpiradas();
    }
}
