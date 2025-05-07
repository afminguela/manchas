package com.manchas.repository;

import com.manchas.model.Solucion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolucionRepository extends JpaRepository<Solucion, Long> {
    List<Solucion> findByTipoMancha(String tipoMancha);
    List<Solucion> findByDificultad(String dificultad);
} 