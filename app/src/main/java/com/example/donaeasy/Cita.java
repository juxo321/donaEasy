package com.example.donaeasy;

import java.io.Serializable;

public class Cita implements Serializable {
    private String fecha;
    private String hora;
    private Campania CampaniaCita;

    public Cita(String fecha, String hora, Campania campaniaCita) {
        this.fecha = fecha;
        this.hora = hora;
        CampaniaCita = campaniaCita;
    }

    public Cita() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Campania getCampaniaCita() {
        return CampaniaCita;
    }

    public void setCampaniaCita(Campania campaniaCita) {
        CampaniaCita = campaniaCita;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", CampaniaCita=" + CampaniaCita +
                '}';
    }
}
