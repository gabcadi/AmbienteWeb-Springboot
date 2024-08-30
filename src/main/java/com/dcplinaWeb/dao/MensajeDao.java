package com.dcplinaWeb.dao;
import com.dcplinaWeb.domain.Mensaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MensajeDao extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findAllByOrderByFechaEnvioDesc();
}
