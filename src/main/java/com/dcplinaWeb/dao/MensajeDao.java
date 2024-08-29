package com.dcplinaWeb.dao;
import com.dcplinaWeb.domain.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MensajeDao extends JpaRepository<Mensaje, Long> {
    
}
