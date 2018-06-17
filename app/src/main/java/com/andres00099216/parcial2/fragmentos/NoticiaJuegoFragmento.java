package com.andres00099216.parcial2.fragmentos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andres00099216.parcial2.R;
import com.andres00099216.parcial2.adaptadores.PagerAdapter;

/**
 * Created by Andres on 16/6/2018.
 */

public class NoticiaJuegoFragmento extends Fragment {
    String game;
    PagerAdapter pagerAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.noticia_juego_recycler, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        pagerAdapter = new PagerAdapter(getChildFragmentManager());

        pagerAdapter.addFragment("Noticias", NoticiaFragmento.newInstance(2, game));
        pagerAdapter.addFragment("Top Players", PlayerFragmento.newInstance(game));


        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = getArguments().getString("game");
        System.out.println(game);
    }


    public static NoticiaJuegoFragmento newInstance(String game) {

        Bundle arguments = new Bundle();
        arguments.putString("game", game);

        NoticiaJuegoFragmento singleNewsFragment = new NoticiaJuegoFragmento();
        singleNewsFragment.setArguments(arguments);

        return singleNewsFragment;
    }


}
