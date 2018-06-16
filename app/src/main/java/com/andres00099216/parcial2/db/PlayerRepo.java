package com.andres00099216.parcial2.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.andres00099216.parcial2.API.Deserializadores.PlayerDes;
import com.andres00099216.parcial2.API.NoticiasAPI;
import com.andres00099216.parcial2.db.Entidades.PlayerEnt;
import com.andres00099216.parcial2.db.daos.PlayerDao;
import com.andres00099216.parcial2.modelo.Item_player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andres on 15/6/2018.
 */

public class PlayerRepo {
    private PlayerDao playerDAO;
    private String token;
    private Context context;

    public PlayerRepo(Application application) {
        db database = db.getInstance(application);
        playerDAO = database.playerDao();
        SharedPreferences sharedPreferences = application.getSharedPreferences("logged", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        context = application.getApplicationContext();

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED){
            getPlayersFromAPI();
        }else {
            Toast.makeText(application, "Couldn't refresh feed", Toast.LENGTH_SHORT).show();
        }

    }

    public LiveData<List<PlayerEnt>> getPlayer(){
        return playerDAO.getPlayer();
    }
    public LiveData<List<PlayerEnt>> getPlayersByGame(String game){
        return playerDAO.getPlayersByGame(game);
    }

    public void getPlayersFromAPI(){
        Gson gson = new GsonBuilder().registerTypeAdapter(Item_player.class, new PlayerDes()).create();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(NoticiasAPI.ENDPOINT).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = retrofitBuilder.build();
        NoticiasAPI apiGameNews = retrofit.create(NoticiasAPI.class);

        Call<List<Item_player>> playersListCall = apiGameNews.getItemPlayer("Beared " + token);
        playersListCall.enqueue(new Callback<List<Item_player>>() {
            @Override
            public void onResponse(Call<List<Item_player>> call, Response<List<Item_player>> response) {
                if (response.code() == 200){
                    for (Item_player players : response.body()){
                        insert(new PlayerEnt(players.getId(), players.getAvatar(), players.getName(), players.getBiografia(), players.getJuego()));
                    }
                }else if (response.code() == 401){
                    Toast.makeText(context, "Session expired", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Item_player>> call, Throwable t) {

            }

        });
    }

    public void insert(PlayerEnt playerEntity){
        new InsertAsyncTask(playerDAO).execute(playerEntity);
    }

    private static class InsertAsyncTask extends AsyncTask<PlayerEnt, Void, Void> {

        private PlayerDao myAsyncTaskPlayerDAO;

        InsertAsyncTask(PlayerDao dao){
            myAsyncTaskPlayerDAO = dao;
        }

        @Override
        protected Void doInBackground(PlayerEnt... playerEntity) {
            myAsyncTaskPlayerDAO.insertPlayer(playerEntity);
            return null;
        }
    }
}
