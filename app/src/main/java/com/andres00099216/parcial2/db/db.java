package com.andres00099216.parcial2.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.andres00099216.parcial2.db.daos.NoticiaDao;
import com.andres00099216.parcial2.db.Entidades.NoticiaEnt;
import com.andres00099216.parcial2.db.daos.PlayerDao;

/**
 * Created by Andres on 13/6/2018.
 */

@Database(
        entities = {NoticiaEnt.class}, exportSchema =  false, version = 1
            )
public abstract class db extends RoomDatabase {


    private static final String DB_NAME = "GameNewsDatabase.db";
    private static volatile db instance;

    public static synchronized db getInstance(Context context){
        if (instance == null){
            instance = create(context);
        }

        return instance;
    }

    private static db create(Context context){
        return Room.databaseBuilder(context, db.class, DB_NAME).build();
    }

    public abstract NoticiaDao NoticiaDao();
    public abstract PlayerDao playerDao();

}