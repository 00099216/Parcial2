package com.andres00099216.parcial2.API.Deserializadores;

import com.andres00099216.parcial2.modelo.Item_new;
import com.andres00099216.parcial2.modelo.Item_user;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andres on 16/6/2018.
 */

public class UserDes implements JsonDeserializer<Item_user> {

    @Override
    public Item_user deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Item_user user = new Item_user();

        JsonObject userJson = json.getAsJsonObject();

        user.setId_usuario(userJson.get("_id").getAsString());
        user.setUsername(userJson.get("user").getAsString());
        user.setPassword(userJson.get("password").getAsString());

        JsonElement newsJsonElement = userJson.get("favoriteNews");
        JsonArray newsJsonArray = newsJsonElement.getAsJsonArray();

        //user.setFavoriteNews(getFavoriteNewsList(newsJsonArray));

        return user;
    }

    private List<Item_new> getFavoriteNewsList(JsonArray jsonArray){
        List<Item_new> newsList = new ArrayList<>();
        for (JsonElement jsonElement:jsonArray){
            Item_new news = new Item_new();

            news.setId(jsonElement.getAsJsonObject().get("_id").getAsString());
            news.setTitle(jsonElement.getAsJsonObject().get("title").getAsString());
            news.setCoverImage(jsonElement.getAsJsonObject().get("coverImage").getAsString());
            news.setCreate_date(jsonElement.getAsJsonObject().get("create_date").getAsString());
            news.setDescription(jsonElement.getAsJsonObject().get("description").getAsString());
            news.setBody(jsonElement.getAsJsonObject().get("body").getAsString());
            news.setGame(jsonElement.getAsJsonObject().get("game").getAsString());

            newsList.add(news);

        }
        return newsList;
    }
}
