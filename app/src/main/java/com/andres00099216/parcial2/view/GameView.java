package com.andres00099216.parcial2.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.andres00099216.parcial2.db.Entidades.GameEnt;
import com.andres00099216.parcial2.db.GameRepo;

import java.util.List;

/**
 * Created by Andres on 16/6/2018.
 */

public class GameView extends AndroidViewModel {

    private GameRepo gameRepo;

    public GameView(@NonNull Application application) {
        super(application);
        gameRepo = new GameRepo(application);
    }

    public void insert(GameEnt gameEnt) {
        gameRepo.insert(gameEnt);
    }

    public LiveData<List<GameEnt>> getAllGames() {
        return gameRepo.getGames();
    }
}
