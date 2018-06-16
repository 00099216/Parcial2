package com.andres00099216.parcial2.API.Deserializadores;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andres on 16/6/2018.
 */

public class GameDes implements JsonDeserializer<List<String>> {

    @Override
    public List<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        List<String> gameList = new ArrayList<>();

        JsonArray gameJsonArray = json.getAsJsonArray();

        for (JsonElement element : gameJsonArray){
            gameList.add(element.getAsString());
        }

        return gameList;
    }
}
