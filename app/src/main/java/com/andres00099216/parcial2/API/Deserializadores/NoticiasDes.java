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

        JsonObject JObject = json.getAsJsonObject();

        if (JObject.get("_id") != null){
            noticias.setId(JObject.get("_id").getAsString());
        }else {
            noticias.setId("");
        }

        if (JObject.get("title") != null){
            noticias.setTitle(JObject.get("title").getAsString());
        }else {
            noticias.setTitle("");
        }

        if (JObject.get("coverImage") != null){
            noticias.setCoverImage(JObject.get("coverImage").getAsString());
        }else {
            noticias.setCoverImage("");
        }

        if (JObject.get("create_date") != null){
            noticias.setCreate_date(JObject.get("created_date").getAsString());
        }else {
            noticias.setCreate_date("");
        }

        if (JObject.get("description") != null){
            noticias.setDescription(JObject.get("description").getAsString());
        }else {
            noticias.setDescription("");
        }

        if (JObject.get("body") != null){
            noticias.setBody(JObject.get("body").getAsString());
        }else {
            noticias.setBody("");
        }

        if (JObject.get("game") != null){
            noticias.setGame(JObject.get("game").getAsString());
        }else {
            noticias.setGame("");
        }

        return noticias;

    }
}
