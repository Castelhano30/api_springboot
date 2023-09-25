package com.trabalhospring.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalhospring.api.model.Lutador;

@Repository
public interface LutadorRepository extends JpaRepository<Lutador, Long> {
    long countById(long Id);  
}
