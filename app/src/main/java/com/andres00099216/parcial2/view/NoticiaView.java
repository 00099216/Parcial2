package com.andres00099216.parcial2.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.andres00099216.parcial2.db.Entidades.NoticiaEnt;
import com.andres00099216.parcial2.db.NoticiasRepo;

import java.util.List;

/**
 * Created by Andres on 14/6/2018.
 */

public class NoticiaView extends AndroidViewModel {

    private NoticiasRepo newsRepository;

    public NoticiaView (@NonNull Application application) {
        super(application);
        newsRepository = new NoticiasRepo(application);
    }

    public void insert(NoticiaEnt newsEntity){
        newsRepository.insert(newsEntity);
    }

    public void update(NoticiaEnt newsEntity){
        newsRepository.insert(newsEntity);
    }

    public LiveData<List<NoticiaEnt>> getAllNewsList() {
        return newsRepository.getAllNews();
    }

    public LiveData<List<NoticiaEnt>> getNewsByGame(String game_name) {
        return newsRepository.getNewsByGameName(game_name);
    }

    public LiveData<List<NoticiaEnt>> getNewsByTitle(String news_title) {
        return newsRepository.getNewsByTitle(news_title);
    }
}
