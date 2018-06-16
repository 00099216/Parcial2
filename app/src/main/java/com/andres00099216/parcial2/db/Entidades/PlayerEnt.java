package com.andres00099216.parcial2.db.Entidades;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Andres on 15/6/2018.
 */
@Entity
public class PlayerEnt {
    @NonNull
    @PrimaryKey
    private String id;
    private String nombre;
    private String desc;
    private String avatar;
    private String juego;

    public PlayerEnt(@NonNull String id, String nombre, String desc, String avatar, String juego) {
        this.id = id;
        this.nombre = nombre;
        this.desc = desc;
        this.avatar = avatar;
        this.juego = juego;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getJuego() {
        return juego;
    }

    public void setJuego(String juego) {
        this.juego = juego;
    }
}
