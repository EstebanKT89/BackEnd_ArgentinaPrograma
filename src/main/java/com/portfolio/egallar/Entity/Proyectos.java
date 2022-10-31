package com.portfolio.egallar.Entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Proyectos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @NotNull
    private String nombreProyec;
    @NotNull
    private Date fecha;
    @NotNull
    private String descripcionProyec;
    private String linkProyec;
    
    
    //Constructores
    public Proyectos() {
    }

    public Proyectos(String nombreProyec, Date fecha, String descripcionProyec, String linkProyec) {
        this.nombreProyec = nombreProyec;
        this.fecha = fecha;
        this.descripcionProyec = descripcionProyec;
        this.linkProyec = linkProyec;
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


    
}
