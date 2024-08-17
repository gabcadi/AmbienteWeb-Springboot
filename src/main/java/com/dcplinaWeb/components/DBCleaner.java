
package com.dcplinaWeb.components;

import com.dcplinaWeb.services.ClaseService;
import com.dcplinaWeb.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DBCleaner {
    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ClaseService claseService;
    
    
    @Scheduled(cron = "0 0 22 * * FRI") // Ejecuta a las 10 PM todos los viernes
    public void limpiarBaseDeDatos() {
        // Primero eliminar las reservas
        reservaService.eliminarTodasLasReservas(); // Implementa este método en ReservaService

        // Luego eliminar las clases
        claseService.eliminarTodasLasClases(); // Implementa este método en ClaseService
    }
    
}
