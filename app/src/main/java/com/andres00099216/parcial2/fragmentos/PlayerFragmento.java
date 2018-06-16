package com.andres00099216.parcial2.fragmentos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andres00099216.parcial2.R;
import com.andres00099216.parcial2.view.PlayerView;

/**
 * Created by Andres on 16/6/2018.
 */

public class PlayerFragmento extends Fragment{
    private PlayerView playerViewModel;

    public static PlayerFragmento newInstance(String game){
        Bundle arguments = new Bundle();
        arguments.putString("game", game);

        PlayerFragmento gamePlayersFragment = new PlayerFragmento();
        gamePlayersFragment.setArguments(arguments);

        return gamePlayersFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycler_item_list, container, false);

        return view;
    }
}
