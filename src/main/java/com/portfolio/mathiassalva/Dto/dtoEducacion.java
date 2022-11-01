package com.portfolio.mathiassalva.Dto;

import javax.validation.constraints.NotBlank;

public class dtoEducacion {
    @NotBlank
    private String eduNombre;
    @NotBlank
    private String eduDesc;

    public dtoEducacion() {
    }

    public dtoEducacion(String eduNombre, String eduDesc) {
        this.eduNombre = eduNombre;
        this.eduDesc = eduDesc;
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
