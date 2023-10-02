package com.trabalhospring.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "luta")
public class Luta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank (message = "Campo local nao pode ser vazio")
    private String local;

    @Column(nullable = false)
    @NotBlank (message = "Campo vitoria nao pode ser vazio")
    private Boolean vitoria;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Boolean getVitoria() {
        return vitoria;
    }

    public void setVitoria(Boolean vitoria) {
        this.vitoria = vitoria;
    }


}