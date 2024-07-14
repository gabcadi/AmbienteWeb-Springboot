
package com.dcplinaWeb.dao;

import com.dcplinaWeb.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author isaac
 */
public interface ReservaDao 
        extends JpaRepository<Reserva, Long> {}
