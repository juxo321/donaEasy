package com.example.donaeasy;

import java.io.Serializable;
import java.util.ArrayList;

public class Donador implements Serializable {
    String id;
    String usuario;
    String contrasena;
    String tipo;
    Boolean testCompleto;
    Cita cita;
    String estatus;
    ArrayList<Boolean> respuestasTest;

    public Donador() {
    }

    public Donador(Boolean testCompleto) {
        this.testCompleto = testCompleto;
    }

    public Donador(String id, String usuario, String contrasena, String tipo, Boolean testCompleto, Cita cita, String estatus, ArrayList<Boolean> respuestasTest) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.testCompleto = testCompleto;
        this.cita = cita;
        this.estatus = estatus;
        this.respuestasTest = respuestasTest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getTestCompleto() {
        return testCompleto;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public void setTestCompleto(Boolean testCompleto) {
        this.testCompleto = testCompleto;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public ArrayList<Boolean> getRespuestasTest() {
        return respuestasTest;
    }

    public void setRespuestasTest(ArrayList<Boolean> respuestasTest) {
        this.respuestasTest = respuestasTest;
    }
}
