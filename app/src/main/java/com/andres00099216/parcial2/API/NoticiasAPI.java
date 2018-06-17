package com.andres00099216.parcial2.API;

import com.andres00099216.parcial2.modelo.Item_new;
import com.andres00099216.parcial2.modelo.item_player;
import com.andres00099216.parcial2.modelo.Item_user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Andres on 12/6/2018.
 */

public interface NoticiasAPI {
    String ENDPOINT = "http://gamenewsuca.herokuapp.com";

    @GET("/news/type/list")
    Call<List<String>> getGames(@Header("Authorization") String authorization);

    @GET("/news")
    Call<List<Item_new>> getNews(@Header("Authorization") String authorization);
    @GET("/users")
    Call<List<Item_user>> getUsers(@Header("Authorization") String authorization);

    @GET("/players")
    Call<List<item_player>> getPlayers(@Header("Authorization") String authorization);

    @FormUrlEncoded
    @POST("/login")
    Call<String> token(@Field("user") String username,@Field("password") String password);

}
