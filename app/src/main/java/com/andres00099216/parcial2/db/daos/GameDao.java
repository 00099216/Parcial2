package com.andres00099216.parcial2.db.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.andres00099216.parcial2.db.Entidades.GameEnt;

import java.util.List;

/**
 * Created by Andres on 16/6/2018.
 */

@Dao
public interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GameEnt... games);

    @Query("SELECT * FROM GameEntity")
    LiveData<List<GameEnt>> getAllGames();

}