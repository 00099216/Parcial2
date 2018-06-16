package com.andres00099216.parcial2.db.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.andres00099216.parcial2.db.Entidades.PlayerEnt;

import java.util.List;

/**
 * Created by Andres on 15/6/2018.
 */
@Dao
public interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlayer(PlayerEnt[] playerEntity);

    @Query("SELECT*FROM PlayerEntity WHERE game=:game")
    LiveData<List<PlayerEnt>> getPlayer(String game);
}
