package com.portfolio.egallar.Entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Proyectos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String nombreProyec;
    private Date fecha;
    private String descripcionProyec;
    private String linkProyec;
    private String imgCap;
    
    
    //Constructores
    public Proyectos() {
    }

    public Proyectos(String nombreProyec, Date fecha, String descripcionProyec, String linkProyec, String imgCap) {
        this.nombreProyec = nombreProyec;
        this.fecha = fecha;
        this.descripcionProyec = descripcionProyec;
        this.linkProyec = linkProyec;
        this.imgCap = imgCap;
    }
    
    
    //Getters y Setters
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombreProyec() {
        return nombreProyec;
    }

    public void setNombreProyec(String nombreProyec) {
        this.nombreProyec = nombreProyec;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcionProyec() {
        return descripcionProyec;
    }

    public void setDescripcionProyec(String descripcionProyec) {
        this.descripcionProyec = descripcionProyec;
    }

    public String getLinkProyec() {
        return linkProyec;
    }

    public void setLinkProyec(String linkProyec) {
        this.linkProyec = linkProyec;
    }

    public String getImgCap() {
        return imgCap;
    }

    public void setImgCap(String imgCap) {
        this.imgCap = imgCap;
    }
    
}
