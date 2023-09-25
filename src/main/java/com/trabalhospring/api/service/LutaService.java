package com.trabalhospring.api.service;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhospring.api.model.Luta;
import com.trabalhospring.api.repository.LutaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LutaService {

    private final LutaRepository lutaRepository;

    //@Autowired
    public LutaService(LutaRepository lutaRepository) {
        this.lutaRepository = lutaRepository;
    }

    public List<Luta> listarTodasLutas() {
        return lutaRepository.findAll();
    }

    public Luta buscarLutaPorId(Long id) {
        Optional<Luta> optionalLuta = lutaRepository.findById(id);
        return optionalLuta.orElse(null);
    }

    public Luta criarLuta(Luta luta) {
        return lutaRepository.save(luta);
    }

    public Luta atualizarLuta(Long id, Luta luta) {
        Optional<Luta> optionalLuta = lutaRepository.findById(id);
        if (optionalLuta.isPresent()) {
            Luta lutaExistente = optionalLuta.get();
            lutaExistente.setLocal(luta.getLocal());
            lutaExistente.setVitoria(luta.getVitoria());
            // Defina qualquer outra propriedade que você precise atualizar aqui

            return lutaRepository.save(lutaExistente);
        } else {
            return null;
        }
    }

    public boolean deletarLuta(Long id) {
        Optional<Luta> optionalLuta = lutaRepository.findById(id);
        if (optionalLuta.isPresent()) {
            lutaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

