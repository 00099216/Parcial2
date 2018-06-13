package com.andres00099216.parcial2.API.Deserializadores;

import com.andres00099216.parcial2.modelo.Item_new;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Andres on 12/6/2018.
 */

public class NoticiasDes implements JsonDeserializer<Item_new>{
    @Override
    public Item_new deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Item_new noticias = new Item_new();

        JsonObject newsJsonObject = json.getAsJsonObject();
        noticias.setId(newsJsonObject.get("id").getAsString());
        noticias.setTitle(newsJsonObject.get("title").getAsString());
        noticias.setBody(newsJsonObject.get("body").getAsString());
        noticias.setGame(newsJsonObject.get("game").getAsString());
        noticias.setCreate_date(newsJsonObject.get("created_date").getAsString());

        noticias.setCover_image(newsJsonObject.get("coverImage").getAsString());
        noticias.setDescription(newsJsonObject.get("description").getAsString());
        return noticias;

    }
}
