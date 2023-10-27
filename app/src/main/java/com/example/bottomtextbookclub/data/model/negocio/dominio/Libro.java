package com.example.bottomtextbookclub.data.model.negocio.dominio;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Libro implements Serializable {


    private String isbn;
    private String nombre;
    private String descripcion;
    private Long rating;

    private List<Categorias> categorias;

    public Libro(String isbn, List<Categorias> categorias) {
        this.isbn = isbn;
        this.categorias = categorias;
    }
    public Libro(String isbn) {
        this.isbn = isbn;
    }
    public Libro(String isbn, ArrayList<Categorias> categorias,String nombre, String descripcion) {
        this.isbn = isbn;
        this.categorias = categorias;
        this.nombre=nombre;
        this.descripcion=descripcion;
    }

    public Libro(String isbn,Categorias categoria,String nombre, String descripcion) {
        this.isbn = isbn;
        ArrayList<Categorias> categoriasList = new ArrayList<>();
        categoriasList.add(categoria);
        this.categorias = categoriasList;
        this.nombre=nombre;
        this.descripcion=descripcion;
    }
    public Libro(String isbn,String nombre, String descripcion) {
        this.isbn = isbn;
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
    public int getIdDeImagen(Context context) {
        String resourceName = "i"+isbn;
        String packageName = context.getPackageName();
        return context.getResources().getIdentifier(resourceName, "drawable", packageName);
    }
    public String getNombreDesdeArcihvo(Context context){
        //por implementar
        return null;
    }
    public String getDescripcionDesdeArcihvo(Context context){
        //por implementar
        return null;
    }
    public void setCategoria(Categorias categoria){
        if (this.categorias == null) {
            List<Categorias> categorias1 = new ArrayList<>();
            this.categorias = categorias1;
        }
    categorias.add(categoria);

    }
    public void setCategoria(List<Categorias> categorias ){
        if (this.categorias == null) {
            List<Categorias> categorias1 = new ArrayList<>();
            this.categorias = categorias1;
        }
        categorias.addAll(categorias);

    }
    public boolean checkCategoria(Categorias categoria) {
        if (this.categorias == null) {return false;}
        return this.categorias.contains(categoria);
    }

    public boolean checkCategoria(List<Categorias> categorias) {
        if (this.categorias == null) {return false;}
        return this.categorias.containsAll(categorias);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return isbn.equals(libro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
