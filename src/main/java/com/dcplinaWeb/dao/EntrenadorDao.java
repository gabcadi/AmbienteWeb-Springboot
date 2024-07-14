/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dcplinaWeb.dao;

import com.dcplinaWeb.domain.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author isaac
 */
public interface EntrenadorDao 
        extends JpaRepository<Entrenador, Long> {}

