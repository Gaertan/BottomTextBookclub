package com.example.bottomtextbookclub.data.model;

import java.util.ArrayList;
import java.util.List;

public class Usuarios {
    private List<Usuario> listaUsuarios;

    public Usuarios() {
        listaUsuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}