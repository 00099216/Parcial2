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

import java.util.List;

/**
 * Created by Andres on 14/6/2018.
 */

public class NoticiaFragmento extends Fragment {
    private NoticiaView newsViewModel;
    private RecyclerView recyclerView;
    private NoticaAdapter newsAdapter;
    private GridLayoutManager gridLayoutManager;
    private String token;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("logged", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        setHasOptionsMenu(true);
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

        newsViewModel.getAllNewsList().observe(this, new Observer<List<NoticiaEnt>>() {
            @Override
            public void onChanged(@Nullable List<NoticiaEnt> newsEntities) {
                newsAdapter.setNewsList(newsEntities);
            }
        });
        return view;
    }

    private List<Item_new> orderNewsList(List<Item_new> newsList) {
        List<Item_new> orderedNewsList = newsList;
        return orderedNewsList;
    }

    public NoticiaFragmento() {
    }

}
