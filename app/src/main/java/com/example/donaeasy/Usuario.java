package com.example.donaeasy;

import java.util.ArrayList;

public class Usuario {
    String usuario;
    String contrasena;
    String tipo;
    Boolean testCompleto;
    ArrayList<Boolean> respuestasTest;

    public Usuario() {
    }

    public Usuario(String usuario, String contrasena, String tipo, Boolean encuestaCompleto, ArrayList<Boolean> respuestasEncuesta) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.encuestaCompleto = encuestaCompleto;
        this.respuestasEncuesta = respuestasEncuesta;
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

    public Boolean getEncuestaCompleto() {
        return encuestaCompleto;
    }

    public void setEncuestaCompleto(Boolean encuestaCompleto) {
        this.encuestaCompleto = encuestaCompleto;
    }

    public ArrayList<Boolean> getRespuestasEncuesta() {
        return respuestasEncuesta;
    }

    public void setRespuestasEncuesta(ArrayList<Boolean> respuestasEncuesta) {
        this.respuestasEncuesta = respuestasEncuesta;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario='" + usuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", tipo='" + tipo + '\'' +
                ", encuestaCompleto=" + encuestaCompleto +
                ", respuestasEncuesta=" + respuestasEncuesta +
                '}';
    }
}
