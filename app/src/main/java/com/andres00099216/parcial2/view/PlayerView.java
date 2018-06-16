package com.andres00099216.parcial2.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.andres00099216.parcial2.db.Entidades.PlayerEnt;
import com.andres00099216.parcial2.db.PlayerRepo;

import java.util.List;

/**
 * Created by Andres on 15/6/2018.
 */

public class PlayerView extends AndroidViewModel {
    private PlayerRepo repository;

    public PlayerView(@NonNull Application application) {
        super(application);
        repository=new PlayerRepo(application);
    }

    public LiveData<List<PlayerEnt>> getPlayersByGame(String game){
        return repository.getPlayer(game);
    }

    public void insert(PlayerEnt playerEntity){
        repository.insert(playerEntity);
    }
}
