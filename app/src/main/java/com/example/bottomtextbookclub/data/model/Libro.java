package com.example.bottomtextbookclub.data.model;

import android.content.Context;

import java.io.Serializable;
import java.util.List;

public class Libro implements Serializable {


    private String isbn;
    private String nombre;
    private String descripcion;

    private List<Categorias> categorias;

    public Libro(String isbn, List<Categorias> categorias) {
        this.isbn = isbn;
        this.categorias = categorias;
    }
    public Libro(String isbn, List<Categorias> categorias,String nombre, String descripcion) {
        this.isbn = isbn;
        this.categorias = categorias;
        this.nombre=nombre;
        this.descripcion=descripcion;
    }
    public String getIsbn() {
        return isbn;
    }

    public List<Categorias> getCategorias() {
        return categorias;
    }


    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public int obtenerIdDeImagen(Context context) {
        String resourceName = "i"+isbn;
        String packageName = context.getPackageName();
        return context.getResources().getIdentifier(resourceName, "drawable", packageName);
    }
    public String obtenerNombreDesdeArcihvo(Context context){
        //por implementar
        return null;
    }
    public String obtenerDescripcionDesdeArcihvo(Context context){
        //por implementar
        return null;
    }

}
