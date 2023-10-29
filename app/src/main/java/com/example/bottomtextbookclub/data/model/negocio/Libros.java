package com.example.bottomtextbookclub.data.model.negocio;

import com.example.bottomtextbookclub.data.model.negocio.dominio.Categorias;
import com.example.bottomtextbookclub.data.model.negocio.dominio.Libro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Libros implements Serializable {

    private static Libros instancia;
    private ArrayList<Libro> listaLibros;

    private Libros() {
        listaLibros = new ArrayList<>();
    }

    public static synchronized Libros getInstancia() {
        if (instancia == null) {
            instancia = new Libros();
        }
        return instancia;
    }

    public void addLibro(Libro libro) {
        if (!listaLibros.contains(libro)) {
            listaLibros.add(libro);
        }
    }
    public void removeLibro(Libro libro) {
        if (listaLibros.contains(libro)) {
            listaLibros.remove(libro);
        }
    }
    public ArrayList<Libro> getLibrosPorCategoria(Categorias categoria) {
        ArrayList<Libro> librosPorCategoria = new ArrayList<>();
        for (Libro libro : listaLibros) {
            if (libro.checkCategoria(categoria)) {
                librosPorCategoria.add(libro);
            }
        }
        return librosPorCategoria;
    }

    public ArrayList<Libro> getLibrosPorCategoria(List<Categorias> categorias) {
        ArrayList<Libro> librosPorCategorias = new ArrayList<>();
        for (Libro libro : listaLibros) {
            if (libro.checkCategoria(categorias)) {
                librosPorCategorias.add(libro);
            }
        }
        return librosPorCategorias;
    }
    public void init(){
        //asegurarse de que se no es nulo
    Libros libros =getInstancia();

    Libro libro1 = new Libro("9788493806125", Categorias.ACCION, "El quijote", "El quijote  asdoimqweormoim qwoiemrioqmrewfewfewgtwrswtgwetfesdgfewfewt32twe li3rnu li32 rnnt43i2l niewfn lies fnlisdfnj slgk o3it ñowmfosdimfoidsmf ew");
    Libro libro2 = new Libro("9788493806126", Categorias.ACCION, "El quijote", "El quijote  asdoimqweormoim qwoiemrioqmr");

        libros.addLibro(libro1);
        libros.addLibro(libro2);




    }



}