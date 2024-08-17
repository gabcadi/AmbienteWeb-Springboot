package com.dcplinaWeb.servicesImpl;

import com.dcplinaWeb.dao.ClaseDao;
import com.dcplinaWeb.dao.ReservaDao;
import com.dcplinaWeb.dao.UsuarioDao;
import com.dcplinaWeb.domain.Clase;
import com.dcplinaWeb.domain.Reserva;
import com.dcplinaWeb.domain.Usuario;
import com.dcplinaWeb.services.ReservaService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaDao reservaDao;
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private ClaseDao claseDao;

    @Override
    public Reserva crearReserva(Long idUsuario, Long idClase) {
        Usuario usuario = usuarioDao.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Clase clase = claseDao.findById(idClase)
            .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        // Verificar si el usuario ya tiene una reserva activa
        Optional<Reserva> reservaExistente = reservaDao.findByUsuarioAndActivaTrue(usuario);
        if (reservaExistente.isPresent()) {
            throw new RuntimeException("El usuario ya tiene una reserva activa");
        }

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setClase(clase);
        reserva.setFechaReserva(LocalDateTime.now());
        reserva.setActiva(true);

        return reservaDao.save(reserva);
    }

    @Override
    public List<Reserva> obtenerReservasUsuario(Long idUsuario) {
        Usuario usuario = usuarioDao.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return reservaDao.findByUsuario(usuario);
    }

    @Override
    public void cancelarReserva(Long id) {
        reservaDao.deleteById(id);
    }

    @Override
    public List<Reserva> obtenerTodasLasReservasActivas() {
        return reservaDao.findByActivaTrue();
    }

    public Clase obtenerClasePorId(Long id) {
        return claseDao.findById(id).orElse(null);
    }


    @Override
    public void eliminarTodasLasReservas() {
        reservaDao.deleteAll();
    }
}
