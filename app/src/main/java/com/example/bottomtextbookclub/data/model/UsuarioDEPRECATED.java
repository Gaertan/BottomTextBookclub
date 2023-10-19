package com.example.bottomtextbookclub.data.model;

public class UsuarioDEPRECATED {
    private String nombre;
    private String password;

    public UsuarioDEPRECATED(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario: " + nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UsuarioDEPRECATED usuario = (UsuarioDEPRECATED) obj;
        return nombre.equals(usuario.nombre) &&
                password.equals(usuario.password);
    }
}