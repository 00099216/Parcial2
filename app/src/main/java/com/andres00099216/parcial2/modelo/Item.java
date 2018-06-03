package com.andres00099216.parcial2.modelo;

/**
 * Created by Andres on 3/6/2018.
 */

public class Item {
    private int id;
    private String titulo;
    private String descripcion;
    private boolean agregado;
    private String foto;

    public Item(int id, String titulo, String descripcion, boolean agregado, String foto) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.agregado = agregado;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isAgregado() {
        return agregado;
    }

    public void setAgregado(boolean agregado) {
        this.agregado = agregado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
