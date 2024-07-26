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

    public Long getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(Long idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}


