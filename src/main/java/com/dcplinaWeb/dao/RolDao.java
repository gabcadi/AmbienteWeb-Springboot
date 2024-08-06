package com.dcplinaWeb.dao;

import com.dcplinaWeb.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolDao extends JpaRepository<Rol, Long> {

    @Query("SELECT r FROM Rol r WHERE r.nombre = :nombre AND r.id_usuario = :id_usuario")
    Rol findByNombreAndIdUsuario(@Param("nombre") String nombre, @Param("id_usuario") Long id_usuario);
}
