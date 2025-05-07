package com.manchas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "manchas")
public class Mancha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String ubicacion;

    @Column(nullable = false)
    private String estado;

    @Column(name = "fecha_reporte")
    private LocalDateTime fechaReporte;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @PrePersist
    protected void onCreate() {
        fechaReporte = LocalDateTime.now();
    }
} 