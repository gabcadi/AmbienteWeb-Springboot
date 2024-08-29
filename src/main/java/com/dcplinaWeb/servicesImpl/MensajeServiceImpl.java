package com.dcplinaWeb.servicesImpl;

import com.dcplinaWeb.dao.MensajeDao;
import com.dcplinaWeb.domain.Mensaje;
import com.dcplinaWeb.services.MensajeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    private MensajeDao mensajeDao;

    @Override
    @Transactional(readOnly = true)
    public List<Mensaje> obtenerTodosLosMensajes() {
        return mensajeDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Mensaje obtenerMensajePorId(Long id) {
        return mensajeDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Mensaje guardarMensaje(Mensaje mensaje) {
        return mensajeDao.save(mensaje);
    }

    @Override
    @Transactional
    public void eliminarMensaje(Long id) {
        mensajeDao.deleteById(id);
    }
}
