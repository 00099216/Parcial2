package com.andres00099216.parcial2.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.andres00099216.parcial2.API.Deserializadores.GameDes;
import com.andres00099216.parcial2.API.NoticiasAPI;
import com.andres00099216.parcial2.db.Entidades.GameEnt;
import com.andres00099216.parcial2.db.daos.GameDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andres on 16/6/2018.
 */

public class GameRepo {

    private GameDao Gdao;
    private String token;
    private Context context;

    public GameRepo(Application application) {

        db base = db.getInstance(application);
        Gdao = base.gameDao();
        SharedPreferences sharedPreferences = application.getSharedPreferences("logged",
                Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        context = application.getApplicationContext();

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            Gson gson = new GsonBuilder().registerTypeAdapter(ArrayList.class, new GameDes()).create();

            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(NoticiasAPI.ENDPOINT).
                    addConverterFactory(GsonConverterFactory.create(gson));
            Retrofit retrofit = builder.build();

            NoticiasAPI apinews = retrofit.create(NoticiasAPI.class);

            Call<List<String>> stringLista = apinews.getGames("Beared " + token);
            stringLista.enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    if (response.code() == 200) {
                        for (String name : response.body()) {
                            insert(new GameEnt(name));
                        }
                    } else if (response.code() == 401) {
                    }
                }

                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        Toast.makeText(context, "Timed Out", Toast.LENGTH_SHORT).show();
                    } else if (t instanceof Exception) {
                        Toast.makeText(context, "Lo sentimos, algo salio mal",
                                Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                }
            });
        }
    }

    public void insert(GameEnt ent) {
        new InsertAsyncTask(Gdao).execute(ent);
    }

    public LiveData<List<GameEnt>> getGames() {
        return Gdao.getJuegos();
    }

    private static class InsertAsyncTask extends AsyncTask<GameEnt, Void, Void> {

        private GameDao inserAsyncGameDao;

        InsertAsyncTask(GameDao dao) {
            inserAsyncGameDao = dao;
        }

        @Override
        protected Void doInBackground(GameEnt... gameEntities) {
            inserAsyncGameDao.insert(gameEntities);
            return null;
        }
    }

}
