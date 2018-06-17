package com.andres00099216.parcial2.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.andres00099216.parcial2.API.Deserializadores.NoticiasDes;
import com.andres00099216.parcial2.API.NoticiasAPI;
import com.andres00099216.parcial2.db.Entidades.NoticiaEnt;
import com.andres00099216.parcial2.db.daos.NoticiaDao;
import com.andres00099216.parcial2.Ordenar;
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
    private Context context;
    private Application application;

    public NoticiasRepo(Application application){
        this.application = application;
    db base = db.getInstance(application);
    noticiaDao = base.NoticiaDao();
    SharedPreferences sp = application.getSharedPreferences("logged", Context.MODE_PRIVATE);
    token = sp.getString("token", "");
    context = application.getApplicationContext();

    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED){
            Gson gson = new GsonBuilder().registerTypeAdapter(Item_new.class, new NoticiasDes()).create();
            Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(NoticiasAPI.ENDPOINT).addConverterFactory(GsonConverterFactory.create(gson));
            Retrofit retrofit = retrofitBuilder.build();
            NoticiasAPI apiGameNews = retrofit.create(NoticiasAPI.class);

            Call<List<Item_new>> newsListCall = apiGameNews.getNews("Beared " + token);
            newsListCall.enqueue(new Callback<List<Item_new>>() {
                @Override
                public void onResponse(Call<List<Item_new>> call, Response<List<Item_new>> response) {
                    if (response.code() == 200){
                        for (Item_new news: response.body()){
                            insert(new NoticiaEnt(news.getId(), news.getTitle(), news.getCoverImage(), Ordenar.Orden(news.getCreate_date()),
                                    news.getDescription(), news.getBody(), news.getGame()));
                        }
                    }else if (response.code() == 401) {
                        Toast.makeText(context, "Session expired", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Item_new>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException){
                        Toast.makeText(context, "Timed Out", Toast.LENGTH_SHORT).show();
                    }else if (t instanceof Exception){
                        Toast.makeText(context, "Something went wrong \n Please try again later", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                }
            });
    }

}

    public LiveData<List<NoticiaEnt>> getAllNews(){
        return noticiaDao.getNews();
    }

    public LiveData<List<NoticiaEnt>> getNewsByGameName(String game){
        return noticiaDao.getNewsByName(game);
    }

    public LiveData<List<NoticiaEnt>> getNewsByTitle(String title){
        return noticiaDao.getNewsByTitle(title);
    }

    public void insert(NoticiaEnt noticiaEnt){
        new InsertAsyncTask(noticiaDao).execute(noticiaEnt);
    }

    public void update(NoticiaEnt noticiaEnt){
        new UpdateAsyncTask(noticiaDao).execute(noticiaEnt);
    }

private static class InsertAsyncTask extends AsyncTask<NoticiaEnt, Void, Void>{

    private NoticiaDao myAsyncTaskNewDAO;

    InsertAsyncTask(NoticiaDao dao){
        myAsyncTaskNewDAO = dao;
    }

    @Override
    protected Void doInBackground(NoticiaEnt... newsEntities) {
        myAsyncTaskNewDAO.insert(newsEntities);
        return null;
    }
}

private static class UpdateAsyncTask extends AsyncTask<NoticiaEnt, Void, Void>{

    private NoticiaDao asyncTaskNoticiaDao;

    UpdateAsyncTask(NoticiaDao dao){
        asyncTaskNoticiaDao = dao;
    }

    @Override
    protected Void doInBackground(NoticiaEnt... newsEntities) {
        asyncTaskNoticiaDao.update(newsEntities);
        return null;
    }
}
}

