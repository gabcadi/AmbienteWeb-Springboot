
package com.dcplinaWeb.components;

import com.dcplinaWeb.domain.Clase;
import com.dcplinaWeb.services.ClaseService;
import jakarta.annotation.PostConstruct;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClaseInitializer {
   @Autowired
    private ClaseService claseService;

    @PostConstruct
    //@Scheduled(cron = "0 0 12 * * SAT") // Ejecuta a las 7 AM todos los domingos
    public void init() {
        // Definir el horario de inicio y fin
        LocalTime inicio = LocalTime.of(7, 0);
        LocalTime fin = LocalTime.of(23, 0);

        // Definir los días de la semana
        List<DayOfWeek> diasSemana = List.of(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY
        );

        // Crear clases para cada hora en cada día de la semana
        for (DayOfWeek dia : diasSemana) {
            LocalDateTime fecha = LocalDateTime.now().with(dia).with(inicio);
            while (fecha.toLocalTime().isBefore(fin)) {
                Clase clase = new Clase();
                clase.setNombre("Personal " + fecha.toLocalTime());
                clase.setDescripcion("Entrenamiento personalizado");
                clase.setDuracion(60); // Duración de 60 minutos
                clase.setFecha(fecha);
                claseService.crearClase(clase);

                // Pasar a la siguiente hora
                fecha = fecha.plusHours(1);
            }
        }
    }
}  

