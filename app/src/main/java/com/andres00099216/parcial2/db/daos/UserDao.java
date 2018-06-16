package com.andres00099216.parcial2.db.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Update;

import com.andres00099216.parcial2.db.Entidades.UserEnt;

/**
 * Created by Andres on 16/6/2018.
 */
@Dao
public interface UserDao {
    @Update
    void update(UserEnt... user);
}
