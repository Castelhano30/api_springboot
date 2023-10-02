package com.trabalhospring.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trabalhospring.api.model.Lutador;
import com.trabalhospring.api.service.LutadorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lutadores")
@Tag (name = "Lutador", description = "API DE LUTADORES")
public class LutadorController {

    private final LutadorService lutadorService;

    @Autowired
    public LutadorController(LutadorService lutadorService) {
        this.lutadorService = lutadorService;
    }

    @GetMapping
    @Operation(summary = "Buscando todos os lutadores", method = "GET")
    public ResponseEntity<List<Lutador>> listarLutadores() {
        List<Lutador> lutadores = lutadorService.listarTodosLutadores();
        return ResponseEntity.ok(lutadores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscando lutadores por ID", method = "GET")
    public ResponseEntity<Lutador> buscarLutadorPorId(@PathVariable Long id) {
        Lutador lutador = lutadorService.buscarLutadorPorId(id);
        if (lutador != null) {
            return ResponseEntity.ok(lutador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Criando lutadores", method = "POST")
    public ResponseEntity<Lutador> criarLutador(@Valid @RequestBody Lutador lutador) {
        Lutador novoLutador = lutadorService.criarLutador(lutador);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLutador);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizando lutador pelo id", method = "PUT")
    public ResponseEntity<Lutador> atualizarLutador(@PathVariable Long id, @Valid @RequestBody Lutador lutador) {
        Lutador lutadorAtualizado = lutadorService.atualizarLutador(id, lutador);
        if (lutadorAtualizado != null) {
            return ResponseEntity.ok(lutadorAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletando lutador por id", method = "DELETE")
    public ResponseEntity<Void> deletarLutador(@PathVariable Long id) {
        boolean deletado = lutadorService.deletarLutador(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

