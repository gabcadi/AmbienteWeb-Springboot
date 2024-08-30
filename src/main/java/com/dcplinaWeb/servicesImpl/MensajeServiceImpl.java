package com.dcplinaWeb.servicesImpl;

import com.dcplinaWeb.dao.MensajeDao;
import com.dcplinaWeb.domain.Mensaje;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MensajeServiceImpl {
    @Autowired
    private MensajeDao mensajeRepository;

    public Mensaje enviarMensaje(String nombreUsuario, String email, String asunto, String contenido) {
        Mensaje mensaje = new Mensaje();
        mensaje.setNombreUsuario(nombreUsuario);
        mensaje.setEmail(email);
        mensaje.setAsunto(asunto);
        mensaje.setContenido(contenido);
        mensaje.setFechaEnvio(LocalDateTime.now());
        mensaje.setLeido(false);
        
        return mensajeRepository.save(mensaje);
    }

    public List<Mensaje> obtenerTodosMensajes() {
        return mensajeRepository.findAllByOrderByFechaEnvioDesc();
    }

    public void marcarComoLeido(Long mensajeId) {
        Mensaje mensaje = mensajeRepository.findById(mensajeId)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
        mensaje.setLeido(true);
        mensajeRepository.save(mensaje);
    }

    public void eliminarMensaje(Long mensajeId) {
        mensajeRepository.deleteById(mensajeId);
    }
}
