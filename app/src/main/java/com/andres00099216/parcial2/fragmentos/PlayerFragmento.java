package com.andres00099216.parcial2.fragmentos;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andres00099216.parcial2.R;
import com.andres00099216.parcial2.adaptadores.PlayerAdapter;
import com.andres00099216.parcial2.view.PlayerView;

/**
 * Created by Andres on 16/6/2018.
 */

public class PlayerFragmento extends Fragment {
    private PlayerView playerView;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private PlayerAdapter playerAdapter;
    private String game;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        playerView = ViewModelProviders.of(this).get(PlayerView.class);

        View view = inflater.inflate(R.layout.recycler_player_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_allPlayers);
        manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        playerAdapter = new PlayerAdapter(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(playerAdapter);

        playerView.getPlayersByGame(game).observe(this, playerEntityList -> {
            playerAdapter.setList(playerEntityList);
        });

        return view;
    }
    public static PlayerFragmento newInstance(String game) {
        Bundle arguments = new Bundle();
        arguments.putString("game", game);

        PlayerFragmento playersFragment = new PlayerFragmento();
        playersFragment.setArguments(arguments);

        return playersFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = getArguments().getString("game");
    }



}
