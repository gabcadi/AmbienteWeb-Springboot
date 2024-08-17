package com.dcplinaWeb.dao;

import com.dcplinaWeb.domain.Clase;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ClaseDao extends CrudRepository<Clase, Long> {

    List<Clase> findByFechaAfter(LocalDateTime fecha);

    void deleteByFechaBefore(LocalDateTime fecha);
    
    
    void deleteAll();


}
