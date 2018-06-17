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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoticiaEnt... news);

    @Update
    void update(NoticiaEnt... news);

    @Query("SELECT * FROM NoticiaEnt ORDER BY notCreateDate DESC")
    LiveData<List<NoticiaEnt>> getNews();

    @Query("SELECT * FROM NoticiaEnt WHERE notGame like :game")
    LiveData<List<NoticiaEnt>> getNewsByName(String game);

    @Query("SELECT * FROM NoticiaEnt WHERE notTittle LIKE :title ORDER BY notCreateDate DESC")
    LiveData<List<NoticiaEnt>> getNewsByTitle(String title);
}

