package com.portfolio.egallar.Dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;

public class DtoProyectos {

    @NotBlank
    private String nombreProyec;
    @NotBlank
    private Date fecha;
    @NotBlank
    private String descripcionProyec;

    private String linkProyec;

    //Constructores
    public DtoProyectos() {
    }

    public DtoProyectos(String nombreProyec, Date fecha, String descripcionProyec, String linkProyec) {
        this.nombreProyec = nombreProyec;
        this.fecha = fecha;
        this.descripcionProyec = descripcionProyec;
        this.linkProyec = linkProyec;
    }
    
    //Getters y Setters

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
