package com.dcplinaWeb.dao;

import com.dcplinaWeb.domain.Clase;
import com.dcplinaWeb.domain.Reserva;
import com.dcplinaWeb.domain.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ReservaDao extends CrudRepository<Reserva, Long> {

    List<Reserva> findByUsuario(Usuario usuario);

    List<Reserva> findByActivaTrue();

    Optional<Reserva> findByUsuarioAndClaseAndActivaTrue(Usuario usuario, Clase clase);

    Optional<Reserva> findByUsuarioAndActivaTrue(Usuario usuario); // Método adicional para encontrar reserva activa del usuario

    void deleteAll();

}
