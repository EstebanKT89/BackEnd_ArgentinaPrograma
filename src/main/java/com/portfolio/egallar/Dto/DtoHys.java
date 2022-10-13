/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.egallar.Dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author Esteban
 */
public class DtoHys {
    
    @NotBlank
    private String nombre;
    @NotBlank
    private int porcentaje;

    //Constructores
    public DtoHys() {
    }

    public DtoHys(String nombreHys, int porcentaje) {
        this.nombre = nombreHys;
        this.porcentaje = porcentaje;
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
