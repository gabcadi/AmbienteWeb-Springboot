package com.dcplinaWeb.services;

import com.dcplinaWeb.domain.Reserva;
import java.util.List;

public interface ReservaService {

    Reserva crearReserva(Long idUsuario, Long idClase);

    List<Reserva> obtenerReservasUsuario(Long idUsuario);

    void cancelarReserva(Long idReserva);

    List<Reserva> obtenerTodasLasReservasActivas();
    
    void eliminarTodasLasReservas();
}
