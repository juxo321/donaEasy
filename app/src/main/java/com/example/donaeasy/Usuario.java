package com.example.donaeasy;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    String id;
    String usuario;
    String contrasena;
    String tipo;
    Boolean testCompleto;
    ArrayList<Boolean> respuestasTest;

    public Usuario() {
    }

    public Usuario(Boolean testCompleto) {
        this.testCompleto = testCompleto;
    }

    public Usuario(String id, String usuario, String contrasena, String tipo, Boolean testCompleto, ArrayList<Boolean> respuestasTest) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.testCompleto = testCompleto;
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

    public void setTestCompleto(Boolean testCompleto) {
        this.testCompleto = testCompleto;
    }

    public ArrayList<Boolean> getRespuestasTest() {
        return respuestasTest;
    }

    public void setRespuestasTest(ArrayList<Boolean> respuestasTest) {
        this.respuestasTest = respuestasTest;
    }
}

