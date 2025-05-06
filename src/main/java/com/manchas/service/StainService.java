package com.manchas.service;

import com.manchas.model.Stain;
import com.manchas.repository.StainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StainService {
    
    @Autowired
    private StainRepository stainRepository;
    
    public Optional<Stain> findSolution(String material, String stainType) {
        return stainRepository.findByMaterialAndStainType(material, stainType);
    }
    
    public Stain saveStain(Stain stain) {
        return stainRepository.save(stain);
    }
} 