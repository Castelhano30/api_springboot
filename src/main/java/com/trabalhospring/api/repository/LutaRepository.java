package com.trabalhospring.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalhospring.api.model.Luta;

@Repository
public interface LutaRepository extends JpaRepository<Luta, Long> {
    long countById(long Id);
}
