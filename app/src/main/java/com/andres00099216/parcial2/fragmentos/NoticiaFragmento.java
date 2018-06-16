package com.andres00099216.parcial2.fragmentos;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andres00099216.parcial2.R;
import com.andres00099216.parcial2.adaptadores.NoticaAdapter;
import com.andres00099216.parcial2.db.Entidades.NoticiaEnt;
import com.andres00099216.parcial2.modelo.Item_new;
import com.andres00099216.parcial2.view.NoticiaView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andres on 14/6/2018.
 */

public class NoticiaFragmento extends Fragment {
    private NoticiaView newsViewModel;
    private RecyclerView recyclerView;
    private NoticaAdapter newsAdapter;
    private GridLayoutManager gridLayoutManager;
    private String token, game;
    private int type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        type = getArguments().getInt("type");
        game = getArguments().getString("game");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        newsViewModel = ViewModelProviders.of(this).get(NoticiaView.class);

        View view = inflater.inflate(R.layout.recycler_item_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerItemView);
        gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position % 3 == 0 ? 2 : 1);
            }
        });
        newsAdapter = new NoticaAdapter(getContext()) {
        };
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(newsAdapter);

        newsViewModel.getAllNewsList().observe(this, newsEntities -> {
            newsAdapter.setNewsList(filterNews(newsEntities));
        });
        return view;
    }

    private List<NoticiaEnt> filterNews(List<NoticiaEnt> newsEntitiesList) {
        List<NoticiaEnt> filteredNewsList = new ArrayList<>();
        if (type == 0) {
            return newsEntitiesList;
        } else if (type == 1) {
            return newsEntitiesList;
        } else {
            for (NoticiaEnt newsEntity : newsEntitiesList) {
                if (newsEntity.getNotGame().equals(game)) {
                    filteredNewsList.add(newsEntity);
                }
            }
            return filteredNewsList;
        }
    }

    public static NoticiaFragmento newInstance(int type, String game) {
        Bundle arguments = new Bundle();
        arguments.putInt("type", type);
        arguments.putString("game", game);

        NoticiaFragmento newsFragment = new NoticiaFragmento();
        newsFragment.setArguments(arguments);
        return newsFragment;
    }

}
