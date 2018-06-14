package com.andres00099216.parcial2.db.Entidades;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Andres on 13/6/2018.
 */

@Entity
public class NoticiaEnt {
    @NonNull
    @PrimaryKey
    private String notId;
    private String notTittle, notCoverImage, notCreateDate, notDescription, notBody, notGame;
    private int notFav;

    public NoticiaEnt(@NonNull String notId, @NonNull String notTittle, @NonNull String notCoverImage, @NonNull String notCreateDate, @NonNull String notDescription, @NonNull String notBody, @NonNull String notGame, @NonNull int notFav) {
        this.notId = notId;
        this.notTittle = notTittle;
        this.notCoverImage = notCoverImage;
        this.notCreateDate = notCreateDate;
        this.notDescription = notDescription;
        this.notBody = notBody;
        this.notGame = notGame;
        this.notFav = notFav;
    }


    @NonNull
    public String getNotId() {
        return notId;
    }

    public void setNotId(@NonNull String notId) {
        this.notId = notId;
    }

    public String getNotTittle() {
        return notTittle;
    }

    public void setNotTittle(String notTittle) {
        this.notTittle = notTittle;
    }


    public String getNotCoverImage() {
        return notCoverImage;
    }

    public void setNotCoverImage(String notCoverImage) {
        this.notCoverImage = notCoverImage;
    }


    public String getNotCreateDate() {
        return notCreateDate;
    }

    public void setNotCreateDate(String notCreateDate) {
        this.notCreateDate = notCreateDate;
    }


    public String getNotDescription() {
        return notDescription;
    }

    public void setNotDescription(String notDescription) {
        this.notDescription = notDescription;
    }


    public String getNotBody() {
        return notBody;
    }

    public void setNotBody(String notBody) {
        this.notBody = notBody;
    }


    public String getNotGame() {
        return notGame;
    }

    public void setNotGame(String notGame) {
        this.notGame = notGame;
    }


    public int getNotFav() {
        return notFav;
    }

    public void setNotFav(int notFav) {
        this.notFav = notFav;
    }
}

