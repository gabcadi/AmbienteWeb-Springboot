package com.dcplinaWeb.services;

import com.dcplinaWeb.domain.Mensaje;
import java.util.List;

public interface MensajeService {

    // Método para obtener todos los mensajes
    List<Mensaje> obtenerTodosLosMensajes();

    // Método para obtener un mensaje por su ID
    Mensaje obtenerMensajePorId(Long id);

    // Método para guardar un mensaje
    Mensaje guardarMensaje(Mensaje mensaje);

    // Método para eliminar un mensaje por su ID
    void eliminarMensaje(Long id);
}
