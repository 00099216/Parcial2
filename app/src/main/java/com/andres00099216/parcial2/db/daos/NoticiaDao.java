package com.andres00099216.parcial2.db.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.andres00099216.parcial2.db.Entidades.NoticiaEnt;

import java.util.List;

@Dao
public interface NoticiaDao {
    @Insert
    void insert(NoticiaEnt... noticias);

    @Update
    void update(NoticiaEnt... noticias);

    @Query("SELECT * FROM NoticiaEnt")
    LiveData<List<NoticiaEnt>> getAllNews();

    @Query("SELECT * FROM NoticiaEnt WHERE notGame =:n_game")
    LiveData<List<NoticiaEnt>> getNewsByGameName(String n_game);

    @Query("SELECT * FROM NoticiaEnt WHERE notTittle =:n_title")
    LiveData<List<NoticiaEnt>> getNewsByTitle(String n_title);
}

