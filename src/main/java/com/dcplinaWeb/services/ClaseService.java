package com.dcplinaWeb.services;

import com.dcplinaWeb.domain.Clase;
import java.time.LocalDate;
import java.util.List;

public interface ClaseService {

    List<Clase> obtenerClasesDisponibles();

    Clase obtenerClasePorId(Long id);
    
    Clase crearClase(Clase clase);
   
    void eliminarClasesExpiradas();
    
    void eliminarTodasLasClases();
}
