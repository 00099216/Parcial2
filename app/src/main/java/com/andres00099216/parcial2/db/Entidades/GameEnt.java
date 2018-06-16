package com.andres00099216.parcial2.db.Entidades;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Andres on 16/6/2018.
 */

public class GameEnt {
    public GameEnt(String game_entity_name) {
        this.game_entity_name = game_entity_name;
    }

    @NonNull
    @PrimaryKey
    private String game_entity_name;

    public String getGame_entity_name() {
        return game_entity_name;
    }

    public void setGame_entity_name(String game_entity_name) {
        this.game_entity_name = game_entity_name;
    }
}
