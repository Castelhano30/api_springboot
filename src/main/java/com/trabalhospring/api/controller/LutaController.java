package com.trabalhospring.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.trabalhospring.api.model.Luta;
import com.trabalhospring.api.model.Lutador;
import com.trabalhospring.api.repository.LutaRepository;
import com.trabalhospring.api.repository.LutadorRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lutador/{idLutador}/lutas")
@Tag (name = "Luta", description = "API DE LUTAS")
public class LutaController {

    private final LutaRepository lutaRepository;

    @Autowired
    private LutadorRepository lutadorRepository;

    @Autowired
    public LutaController(LutaRepository lutaRepository) {
        this.lutaRepository = lutaRepository;
    }

    @GetMapping
     @Operation(summary = "Buscando todas as lutas", method = "GET")
    public List<Luta> getAllLutas() {
        return lutaRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscando lutas por ID", method = "GET")
    public Luta getLutaById(@PathVariable Long id) {
        return lutaRepository.findById(id).orElse(null);
    }

    @PostMapping()
    @Operation(summary = "Criando luta", method = "POST")
    public ResponseEntity<Luta> createLuta(@PathVariable("idLutador") Long idLutador, @RequestBody Luta luta) {
        try {
            // Primeiro, verifique se o lutador existe pelo ID.
            Optional<Lutador> lutador = lutadorRepository.findById(idLutador);

            if (!lutador.isPresent())
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            lutador.get().adicionarLuta(luta);
            this.lutadorRepository.save(lutador.get());

            return new ResponseEntity<>(luta, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizando luta", method = "PUT")
    public Luta updateLuta(@PathVariable Long id, @RequestBody Luta updatedLuta) {
        return lutaRepository.findById(id)
                .map(luta -> {
                    luta.setLocal(updatedLuta.getLocal());
                    luta.setVitoria(updatedLuta.getVitoria());
                    return lutaRepository.save(luta);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletando luta", method = "DELETE")
    public void deleteLuta(@PathVariable Long id) {
        lutaRepository.deleteById(id);
    }
}
