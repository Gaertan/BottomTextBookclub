package com.example.bottomtextbookclub.data.model;

public enum Categorias {
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

    @Override
    public String toString() {
        return nombre;
    }
}