package com.andres00099216.parcial2.API.Deserializadores;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Andres on 12/6/2018.
 */

public class TokenDes implements JsonDeserializer<String> {

        @Override
        public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            String token = "";

            if (json.getAsJsonObject() != null){
                JsonObject tokenJson = json.getAsJsonObject();
                token = tokenJson.get("token").getAsString();
            }

            return token;
    }
}
