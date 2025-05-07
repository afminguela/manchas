package com.manchas.controller;

import com.manchas.model.Solucion;
import com.manchas.repository.SolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soluciones")
@CrossOrigin(origins = "*")
public class SolucionController {

    @Autowired
    private SolucionRepository solucionRepository;

    @GetMapping
    public List<Solucion> getAllSoluciones() {
        return solucionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solucion> getSolucionById(@PathVariable Long id) {
        return solucionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipoMancha}")
    public List<Solucion> getSolucionesByTipoMancha(@PathVariable String tipoMancha) {
        return solucionRepository.findByTipoMancha(tipoMancha);
    }

    @GetMapping("/dificultad/{dificultad}")
    public List<Solucion> getSolucionesByDificultad(@PathVariable String dificultad) {
        return solucionRepository.findByDificultad(dificultad);
    }

    @PostMapping
    public Solucion createSolucion(@RequestBody Solucion solucion) {
        return solucionRepository.save(solucion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solucion> updateSolucion(@PathVariable Long id, @RequestBody Solucion solucionDetails) {
        return solucionRepository.findById(id)
                .map(solucion -> {
                    solucion.setNombre(solucionDetails.getNombre());
                    solucion.setDescripcion(solucionDetails.getDescripcion());
                    solucion.setTipoMancha(solucionDetails.getTipoMancha());
                    solucion.setPasos(solucionDetails.getPasos());
                    solucion.setTiempoEstimado(solucionDetails.getTiempoEstimado());
                    solucion.setDificultad(solucionDetails.getDificultad());
                    return ResponseEntity.ok(solucionRepository.save(solucion));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSolucion(@PathVariable Long id) {
        return solucionRepository.findById(id)
                .map(solucion -> {
                    solucionRepository.delete(solucion);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 