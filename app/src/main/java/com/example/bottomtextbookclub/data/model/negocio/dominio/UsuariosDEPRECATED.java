package com.example.bottomtextbookclub.data.model.negocio.dominio;

import java.util.ArrayList;
import java.util.List;

public class UsuariosDEPRECATED {
    private List<UsuarioDEPRECATED> listaUsuarios;

    public UsuariosDEPRECATED() {
        listaUsuarios = new ArrayList<>();
    }

    public void agregarUsuario(UsuarioDEPRECATED usuario) {
        listaUsuarios.add(usuario);
    }

    public List<UsuarioDEPRECATED> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioDEPRECATED> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}