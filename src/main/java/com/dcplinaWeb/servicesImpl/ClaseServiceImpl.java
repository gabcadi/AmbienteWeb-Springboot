package com.dcplinaWeb.servicesImpl;

import com.dcplinaWeb.dao.ClaseDao;
import com.dcplinaWeb.dao.ReservaDao;
import com.dcplinaWeb.domain.Clase;
import com.dcplinaWeb.domain.Reserva;
import com.dcplinaWeb.services.ClaseService;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClaseServiceImpl implements ClaseService {

    @Autowired
    private ClaseDao claseDao;
    @Autowired
    private ReservaDao reservaDao;

    @Override
    public List<Clase> obtenerClasesDisponibles() {
        // Obtener todas las clases futuras
        List<Clase> todasLasClases = claseDao.findByFechaAfter(LocalDateTime.now());

        // Obtener todas las clases que ya están reservadas
        List<Reserva> reservasActivas = reservaDao.findByActivaTrue();
        List<Long> clasesReservadasIds = reservasActivas.stream()
                .map(reserva -> reserva.getClase().getId())
                .collect(Collectors.toList());

        // Filtrar las clases que no están reservadas
        return todasLasClases.stream()
                .filter(clase -> !clasesReservadasIds.contains(clase.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Clase obtenerClasePorId(Long id) {
        return claseDao.findById(id).orElse(null);
    }

    @Override
    public Clase crearClase(Clase clase) {
        return claseDao.save(clase);
    }

    @Override
    public void eliminarClasesExpiradas() {
        LocalDateTime ahora = LocalDateTime.now();
        claseDao.deleteByFechaBefore(ahora);
    }

    @Override
    public void eliminarTodasLasClases() {
        claseDao.deleteAll();
    }
}
