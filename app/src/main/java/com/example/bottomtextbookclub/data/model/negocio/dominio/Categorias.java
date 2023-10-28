package com.example.bottomtextbookclub.data.model.negocio.dominio;

import java.io.Serializable;

public enum Categorias implements Serializable {
    DEFAULT("@string/defaultCategory"),
    TERROR("@string/terror"),
    ACCION("@string/accion"),
    COMEDIA("@string/comedia"),
    COMERCIALES("@string/comerciales"),
    DETECTIVE("@string/detective"),
    DRAMA("@string/drama");

    private String nombre;

    private Categorias(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre(){return this.name().toString().toLowerCase();}
    @Override
    public String toString() {
        return nombre;
    }
}