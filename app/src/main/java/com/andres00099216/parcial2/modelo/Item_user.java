package com.andres00099216.parcial2.modelo;

import java.util.List;

/**
 * Created by Andres on 12/6/2018.
 */

public class Item_user {
    public String id_usuario;
    public String username;
    public String password;
    public List<Item_user> favoriteNews;

    public Item_user() {
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Item_user> getFavoriteNews() {
        return favoriteNews;
    }

    public void setFavoriteNews(List<Item_user> favoriteNews) {
        this.favoriteNews = favoriteNews;
    }
}
