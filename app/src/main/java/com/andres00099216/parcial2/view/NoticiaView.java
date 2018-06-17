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

    private NoticiasRepo noticiasRepo;

    public NoticiaView(@NonNull Application application) {
        super(application);
        noticiasRepo = new NoticiasRepo(application);
    }

    public void insert(NoticiaEnt noticiaEnt) {
        noticiasRepo.insert(noticiaEnt);
    }

    public void update(NoticiaEnt noticiaEnt) {
        noticiasRepo.insert(noticiaEnt);
    }

    public LiveData<List<NoticiaEnt>> getAllNewsList() {
        return noticiasRepo.getAllNews();
    }

    public LiveData<List<NoticiaEnt>> getNewsByGame(String game_name) {
        return noticiasRepo.getNewsByGameName(game_name);
    }

    public LiveData<List<NoticiaEnt>> getNewsByTitle(String news_title) {
        return noticiasRepo.getNewsByTitle(news_title);
    }
}
