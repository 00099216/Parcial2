package com.andres00099216.parcial2.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.andres00099216.parcial2.API.Deserializadores.NoticiasDes;
import com.andres00099216.parcial2.API.NoticiasAPI;
import com.andres00099216.parcial2.db.Entidades.NoticiaEnt;
import com.andres00099216.parcial2.db.daos.NoticiaDao;
import com.andres00099216.parcial2.modelo.Item_new;
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
 * Created by Andres on 13/6/2018.
 */

public class NoticiasRepo {

    private NoticiaDao noticiaDao;
    private String token;
    private Context t;

    public NoticiasRepo(Application application) {
        db base = db.getInstance(application);
        noticiaDao = base.NoticiaDao();
        SharedPreferences sp = application.getSharedPreferences("logged", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        t = application.getApplicationContext();
        Gson gson = new GsonBuilder().registerTypeAdapter(Item_new.class, new NoticiasDes()).create();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(NoticiasAPI.ENDPOINT).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = retrofitBuilder.build();
        NoticiasAPI apiGameNews = retrofit.create(NoticiasAPI.class);

        Call<List<Item_new>> newsListCall = apiGameNews.getItemNoticias("Beared " + token);
        newsListCall.enqueue(new Callback<List<Item_new>>() {
            @Override
            public void onResponse(Call<List<Item_new>> call, Response<List<Item_new>> response) {
                System.out.println(response.code());
                for (Item_new noticia : response.body()) {
                    insert(new NoticiaEnt(noticia.getId(), noticia.getTitle(), noticia.getCoverImage(), noticia.getCreate_date(),
                            noticia.getDescription(), noticia.getBody(), noticia.getGame()));
                }
            }

            @Override
            public void onFailure(Call<List<Item_new>> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    Toast.makeText(NoticiasRepo.this.t, "Timed Out", Toast.LENGTH_SHORT).show();
                } else if (t instanceof Exception) {
                    Toast.makeText(NoticiasRepo.this.t, "Something went wrong \n Please try again later", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            }
        });
    }


    public LiveData<List<NoticiaEnt>> getAllNews() {
        return noticiaDao.getAllNews();
    }

    public LiveData<List<NoticiaEnt>> getNewsByGameName(String game) {
        return noticiaDao.getNewsByGameName(game);
    }

    public LiveData<List<NoticiaEnt>> getNewsByTitle(String title) {
        return noticiaDao.getNewsByTitle(title);
    }


    private static class InsertAsync extends AsyncTask<NoticiaEnt, Void, Void> {

        private NoticiaDao noticiaAsyncIn;

        InsertAsync(NoticiaDao dao) {
            noticiaAsyncIn = dao;
        }

        @Override
        protected Void doInBackground(NoticiaEnt... newsEntities) {
            noticiaAsyncIn.insert(newsEntities);
            return null;
        }
    }

    private static class UpdateAsync extends AsyncTask<NoticiaEnt, Void, Void> {

        private NoticiaDao noticiaAsyncUp;

        UpdateAsync(NoticiaDao dao) {
            noticiaAsyncUp = dao;
        }

        @Override
        protected Void doInBackground(NoticiaEnt... newsEntities) {
            noticiaAsyncUp.update(newsEntities);
            return null;
        }
    }

    public void insert(NoticiaEnt newsEntity) {
        new InsertAsync(noticiaDao).execute(newsEntity);
    }

    public void update(NoticiaEnt newsEntity) {
        new UpdateAsync(noticiaDao).execute(newsEntity);
    }
}

