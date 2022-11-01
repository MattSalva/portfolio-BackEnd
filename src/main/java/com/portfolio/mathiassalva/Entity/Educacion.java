package com.portfolio.mathiassalva.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Educacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String eduNombre;
    private String eduDesc;

    public Educacion() {
    }

    public Educacion(String eduNombre, String eduDesc) {
        this.eduNombre = eduNombre;
        this.eduDesc = eduDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEduNombre() {
        return eduNombre;
    }

    public void setEduNombre(String eduNombre) {
        this.eduNombre = eduNombre;
    }

    public String getEduDesc() {
        return eduDesc;
    }

    public void setEduDesc(String eduDesc) {
        this.eduDesc = eduDesc;
    }
}
