package com.portfolio.egallar.Dto;

import javax.validation.constraints.NotBlank;

public class DtoExperiencia {

    @NotBlank
    private String nombreExp;
    @NotBlank
    private String descripcionExp;

    //Constructores
    public DtoExperiencia() {
    }

    public DtoExperiencia(String nombreExp, String descripcionExp) {
        this.nombreExp = nombreExp;
        this.descripcionExp = descripcionExp;
    }

    //Getters y Setters
    public String getNombreExp() {
        return nombreExp;
    }

    public void setNombreExp(String nombreExp) {
        this.nombreExp = nombreExp;
    }

    public String getDescripcionExp() {
        return descripcionExp;
    }

    public void setDescripcionExp(String descripcionExp) {
        this.descripcionExp = descripcionExp;
    }
    
}
