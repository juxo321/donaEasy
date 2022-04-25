package com.example.donaeasy;

import java.io.Serializable;

public class Campania implements Serializable {
    private String nombrePaciente;
    private int donadoresNecesarios;
    private String tipoSangre;
    private String ubicacion;
    private String descripcion;

    public Campania(String nombrePaciente, int donadoresNecesarios, String tipoSangre, String ubicacion, String descripcion) {
        this.nombrePaciente = nombrePaciente;
        this.donadoresNecesarios = donadoresNecesarios;
        this.tipoSangre = tipoSangre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }

    public Campania() {
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public int getDonadoresNecesarios() {
        return donadoresNecesarios;
    }

    public void setDonadoresNecesarios(int donadoresNecesarios) {
        this.donadoresNecesarios = donadoresNecesarios;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
