package com.manchas.repository;

import com.manchas.model.Mancha;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ManchaRepository extends JpaRepository<Mancha, Long> {
    List<Mancha> findByUsuarioId(Long usuarioId);
    List<Mancha> findByEstado(String estado);
    List<Mancha> findByTipo(String tipo);
} 