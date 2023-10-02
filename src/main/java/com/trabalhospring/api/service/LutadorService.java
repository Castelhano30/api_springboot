package com.trabalhospring.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhospring.api.model.Lutador;
import com.trabalhospring.api.repository.LutadorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LutadorService {

    private final LutadorRepository lutadorRepository;

    @Autowired
    public LutadorService(LutadorRepository lutadorRepository) {
        this.lutadorRepository = lutadorRepository;
    }

    public List<Lutador> listarTodosLutadores() {
        return lutadorRepository.findAll();
    }

    public Lutador buscarLutadorPorId(Long id) {
        Optional<Lutador> optionalLutador = lutadorRepository.findById(id);
        return optionalLutador.orElse(null);
    }

    public Lutador criarLutador(Lutador lutador) {
        return lutadorRepository.save(lutador);
    }

    public Lutador atualizarLutador(Long id, Lutador lutador) {
        Optional<Lutador> optionalLutador = lutadorRepository.findById(id);
        if (optionalLutador.isPresent()) {
            Lutador lutadorExistente = optionalLutador.get();
            lutadorExistente.setNome(lutador.getNome());
            lutadorExistente.setCpf(lutador.getCpf());

            return lutadorRepository.save(lutadorExistente);
        } else {
            return null;
        }
    }

    public boolean deletarLutador(Long id) {
        Optional<Lutador> optionalLutador = lutadorRepository.findById(id);
        if (optionalLutador.isPresent()) {
            lutadorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

