package com.andres00099216.parcial2.API.Deserializadores;

import com.andres00099216.parcial2.modelo.Item_player;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Andres on 15/6/2018.
 */

public class PlayerDes implements JsonDeserializer<Item_player> {

    @Override
    public Item_player deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Item_player player = new Item_player();

        JsonObject playerJsonObject = json.getAsJsonObject();

        if (playerJsonObject.get("_id") != null){
            player.setId(playerJsonObject.get("_id").getAsString());
        }else {
            player.setId("");
        }

        if (playerJsonObject.get("name") != null){
            player.setName(playerJsonObject.get("name").getAsString());
        }else {
            player.setName("");
        }

        if (playerJsonObject.get("biografia") != null){
            player.setBiografia(playerJsonObject.get("biografia").getAsString());
        }else {
            player.setBiografia("");
        }

        if (playerJsonObject.get("avatar") != null){
            player.setAvatar(playerJsonObject.get("avatar").getAsString());
        }else {
            player.setAvatar("");
        }

        if (playerJsonObject.get("game") != null){
            player.setJuego(playerJsonObject.get("game").getAsString());
        }else {
            player.setJuego("");
        }

        return player;
    }
}
