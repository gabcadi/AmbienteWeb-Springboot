/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dcplinaWeb.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "entrenador")
public class Entrenador implements Serializable {

    private static final long serialVersionUID = 11;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento
    @Column(name = "id_entrenador") // Especifica el nombre de la columna (opcional)
    private Long idEntrenador;

    @Column(nullable = false) // No puede ser nulo
    private String nombre;

    @Column(nullable = false, unique = true) // No puede ser nulo, debe ser único
    private String email;

    @Column(nullable = false) // No puede ser nulo
    private String contrasena;

    // Constructor vacío (requerido por JPA)
    public Entrenador() {
    }

    public Entrenador(String nombre, String email, String contrasena) {

        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }

}
