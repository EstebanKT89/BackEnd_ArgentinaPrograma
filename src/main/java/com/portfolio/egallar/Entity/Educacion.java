package com.portfolio.egallar.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Educacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String nombreEduc;
    private String descripcionEduc;

    //Constructores
    public Educacion() {
    }

    public Educacion(String nombreEduc, String descripcionEduc) {
        this.nombreEduc = nombreEduc;
        this.descripcionEduc = descripcionEduc;
    }
    
    //Getters y Setters
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

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
