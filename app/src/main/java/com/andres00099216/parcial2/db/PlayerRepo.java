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
import com.andres00099216.parcial2.modelo.item_player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.SocketTimeoutException;
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
    private PlayerDao playerDao;
    private String token;
    private Context context;

    public PlayerRepo(Application application) {
        db base = db.getInstance(application);
        playerDao = base.playerDao();
        SharedPreferences sp = application.getSharedPreferences("logged", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        context = application.getApplicationContext();

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) {
            Gson gson = new GsonBuilder().registerTypeAdapter(item_player.class, new PlayerDes()).create();
            Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(NoticiasAPI.ENDPOINT).
                    addConverterFactory(GsonConverterFactory.create(gson));
            Retrofit retrofit = retrofitBuilder.build();
            NoticiasAPI apiGameNews = retrofit.create(NoticiasAPI.class);

            Call<List<item_player>> playersListCall = apiGameNews.getPlayers("Beared " + token);
            playersListCall.enqueue(new Callback<List<item_player>>() {
                @Override
                public void onResponse(Call<List<item_player>> call, Response<List<item_player>> response) {
                    if (response.code() == 200) {
                        for (item_player players : response.body()) {
                            insert(new PlayerEnt(players.getId(), players.getAvatar(), players.getName(),
                                    players.getBiografia(), players.getJuego()));
                        }
                    } else if (response.code() == 401) {

                    }
                }

                @Override
                public void onFailure(Call<List<item_player>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        Toast.makeText(context, "Timed Out", Toast.LENGTH_SHORT).show();
                    } else if (t instanceof Exception) {
                        Toast.makeText(context, "Intentelo mas tarde", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                }

            });
        }

    }

    public LiveData<List<PlayerEnt>> getAllPlayers() {
        return playerDao.getPlayer();
    }

    public LiveData<List<PlayerEnt>> getPlayersByGame(String game) {
        return playerDao.getPlayersByGame(game);
    }


    public void insert(PlayerEnt playerEnt) {
        new InsertAsyncTask(playerDao).execute(playerEnt);
    }

    private static class InsertAsyncTask extends AsyncTask<PlayerEnt, Void, Void> {

        private PlayerDao asyncTaskPlayerDao;

        InsertAsyncTask(PlayerDao dao) {
            asyncTaskPlayerDao = dao;
        }

        @Override
        protected Void doInBackground(PlayerEnt... playerEnts) {
            asyncTaskPlayerDao.insert(playerEnts);
            return null;
        }
    }
}
