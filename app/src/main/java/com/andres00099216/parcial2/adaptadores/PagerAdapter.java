package com.andres00099216.parcial2.adaptadores;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andres on 14/6/2018.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    private List<String> tl;
    private List<Fragment> fl;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        tl = new ArrayList<>();
        fl = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fl.get(position);
    }

    @Override
    public int getCount() {
        return fl.size();
    }

    public void addFragment(String t, Fragment f) {
        tl.add(t);
        fl.add(f);

    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tl.get(position);
    }
}
