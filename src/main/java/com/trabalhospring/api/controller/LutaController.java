package com.trabalhospring.api.controller;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.trabalhospring.api.model.Luta;
import com.trabalhospring.api.repository.LutaRepository;

import java.util.List;

@RestController
@RequestMapping("/lutas")
public class LutaController {

    private final LutaRepository lutaRepository;

    //@Autowired
    public LutaController(LutaRepository lutaRepository) {
        this.lutaRepository = lutaRepository;
    }

    @GetMapping
    public List<Luta> getAllLutas() {
        return lutaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Luta getLutaById(@PathVariable Long id) {
        return lutaRepository.findById(id).orElse(null);
    }

    @PostMapping("/{id}")
    public Luta createLuta(@PathVariable Long id, @RequestBody Luta luta) {
        return lutaRepository.save(luta);
    }
    

    @PutMapping("/{id}")
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
    public void deleteLuta(@PathVariable Long id) {
        lutaRepository.deleteById(id);
    }
}



