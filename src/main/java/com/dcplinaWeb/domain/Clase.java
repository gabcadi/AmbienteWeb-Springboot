package com.dcplinaWeb.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import lombok.Data;

@Data
@Entity
@Table(name = "clase")
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clase")
    private Long idClase;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private Time hora;

    @ManyToOne // Relación muchos a uno con Entrenador
    @JoinColumn(name = "id_entrenador") // Nombre de la columna de clave externa
    private Entrenador entrenador;

    public Clase() {
    }

    public Clase(Date fecha, Time hora, Entrenador entrenador) {
        this.fecha = fecha;
        this.hora = hora;
        this.entrenador = entrenador;
    }

}
