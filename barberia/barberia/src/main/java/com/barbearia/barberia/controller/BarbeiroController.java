package com.barbearia.barberia.controller;

import com.barbearia.barberia.model.Barbeiro;
import com.barbearia.barberia.service.BarbeiroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbeiros")
@RequiredArgsConstructor
public class BarbeiroController {

    private final BarbeiroService service;

    @PostMapping
    public ResponseEntity<Barbeiro> criar(@Valid @RequestBody Barbeiro barbeiro) {
        return ResponseEntity.ok(service.criar(barbeiro));
    }

    @GetMapping
    public List<Barbeiro> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barbeiro> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barbeiro> atualizar(@PathVariable Long id, @Valid @RequestBody Barbeiro barbeiro) {
        return ResponseEntity.ok(service.atualizar(id, barbeiro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}