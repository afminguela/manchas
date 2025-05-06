package com.manchas.controller;

import com.manchas.model.Stain;
import com.manchas.service.StainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/stains")
@CrossOrigin(origins = "*") // Permitir peticiones desde cualquier origen
public class StainController {

    @Autowired
    private StainService stainService;

    @GetMapping("/search")
    public ResponseEntity<?> findSolution(
            @RequestParam String material,
            @RequestParam String stainType) {
        
        Optional<Stain> solution = stainService.findSolution(material, stainType);
        
        if (solution.isPresent()) {
            return ResponseEntity.ok(solution.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Stain> saveStain(@RequestBody Stain stain) {
        Stain savedStain = stainService.saveStain(stain);
        return ResponseEntity.ok(savedStain);
    }
} 