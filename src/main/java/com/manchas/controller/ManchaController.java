package com.manchas.controller;

import com.manchas.model.Mancha;
import com.manchas.repository.ManchaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manchas")
@CrossOrigin(origins = "*")
public class ManchaController {

    @Autowired
    private ManchaRepository manchaRepository;

    @GetMapping
    public List<Mancha> getAllManchas() {
        return manchaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mancha> getManchaById(@PathVariable Long id) {
        return manchaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Mancha> getManchasByUsuario(@PathVariable Long usuarioId) {
        return manchaRepository.findByUsuarioId(usuarioId);
    }

    @GetMapping("/estado/{estado}")
    public List<Mancha> getManchasByEstado(@PathVariable String estado) {
        return manchaRepository.findByEstado(estado);
    }

    @GetMapping("/tipo/{tipo}")
    public List<Mancha> getManchasByTipo(@PathVariable String tipo) {
        return manchaRepository.findByTipo(tipo);
    }

    @PostMapping
    public Mancha createMancha(@RequestBody Mancha mancha) {
        return manchaRepository.save(mancha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mancha> updateMancha(@PathVariable Long id, @RequestBody Mancha manchaDetails) {
        return manchaRepository.findById(id)
                .map(mancha -> {
                    mancha.setTipo(manchaDetails.getTipo());
                    mancha.setDescripcion(manchaDetails.getDescripcion());
                    mancha.setUbicacion(manchaDetails.getUbicacion());
                    mancha.setEstado(manchaDetails.getEstado());
                    return ResponseEntity.ok(manchaRepository.save(mancha));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMancha(@PathVariable Long id) {
        return manchaRepository.findById(id)
                .map(mancha -> {
                    manchaRepository.delete(mancha);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 