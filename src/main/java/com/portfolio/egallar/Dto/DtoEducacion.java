package com.portfolio.egallar.Dto;

import javax.validation.constraints.NotBlank;

public class DtoEducacion {

    @NotBlank
    private String nombreEduc;
    @NotBlank
    private String descripcionEduc;

    //Constructores
    public DtoEducacion() {
    }

    public DtoEducacion(String nombreEduc, String descripcionEduc) {
        this.nombreEduc = nombreEduc;
        this.descripcionEduc = descripcionEduc;
    }

    //Getters y Setters
    public String getNombreEduc() {
        return nombreEduc;
    }

    public void setNombreEduc(String nombreEduc) {
        this.nombreEduc = nombreEduc;
    }

    public String getDescripcionEduc() {
        return descripcionEduc;
    }

    public void setDescripcionEduc(String descripcionEduc) {
        this.descripcionEduc = descripcionEduc;
    }

}
