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


    private NoticiasRepo Nrepo;

    public NoticiaView (@NonNull Application application) {
        super(application);
        Nrepo = new NoticiasRepo(application);
    }

    public LiveData<List<NoticiaEnt>> getAllNewsList() {
        return Nrepo.getAllNews();
    }
}
