package com.manchas.repository;

import com.manchas.model.Stain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StainRepository extends JpaRepository<Stain, Long> {
    Optional<Stain> findByMaterialAndStainType(String material, String stainType);
} 