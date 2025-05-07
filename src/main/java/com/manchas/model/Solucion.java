package com.manchas.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "soluciones")
public class Solucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "tipo_mancha")
    private String tipoMancha;

    @Column(columnDefinition = "TEXT")
    private String pasos;

    @Column(name = "tiempo_estimado")
    private Integer tiempoEstimado;

    private String dificultad;
} 