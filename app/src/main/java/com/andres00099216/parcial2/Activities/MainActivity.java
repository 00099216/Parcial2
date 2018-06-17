package com.andres00099216.parcial2.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.andres00099216.parcial2.R;
import com.andres00099216.parcial2.db.Entidades.GameEnt;
import com.andres00099216.parcial2.db.db;
import com.andres00099216.parcial2.fragmentos.NoticiaFragmento;
import com.andres00099216.parcial2.fragmentos.NoticiaJuegoFragmento;
import com.andres00099216.parcial2.view.GameView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;


    db gameNewsDatabase;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = this.getSharedPreferences("logged", MODE_PRIVATE);
        Intent intentL = new Intent(this, LoginActivity.class);

        if (sharedPreferences.contains("token")){
            super.onCreate(savedInstanceState);
            gameNewsDatabase = db.getInstance(getApplicationContext());

            setContentView(R.layout.main_activity);

            gameView = ViewModelProviders.of(this).get(GameView.class);

            gameView.getAllGames().observe(this, new Observer<List<GameEnt>>() {
                @Override
                public void onChanged(@Nullable List<GameEnt> gameEntities) {
                    setGameMenu(gameEntities);
                }
            });
            drawerLayout = findViewById(R.id.drawerLayout);
            navigationView = findViewById(R.id.navigationView);
            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            navigationView.getMenu().findItem(R.id.menu_news).setChecked(true);
            Fragment frag = NoticiaFragmento.newInstance(0, "");
            getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout, frag).commit();
            getSupportActionBar().setTitle("Noticias");

            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icono_menu);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            navigationView.setNavigationItemSelectedListener(item -> {
                {
                    Fragment fragment = new Fragment();

                    switch (item.getItemId()) {
                        case R.id.menu_news:
                            fragment = NoticiaFragmento.newInstance(0, "");
                            break;

                        case R.id.settings:
                            logoutButtonClicked();
                            break;

                        default:
                            fragment = NoticiaJuegoFragmento.newInstance(item.getTitle().toString());
                            break;
                    }

                    if (fragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout,
                                fragment).commit();
                        getSupportActionBar().setTitle(item.getTitle());
                        item.setChecked(true);
                    } else {
                        item.setChecked(false);
                    }

                    drawerLayout.closeDrawers();

                    return true;
                }
            });
        }else {
            startActivity(intentL);
            finish();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void setGameMenu(List<GameEnt> gamesList) {
        navigationView.getMenu().findItem(R.id.item_menuGames).getSubMenu().clear();

        for (GameEnt gameEntity : gamesList) {
            navigationView.getMenu().findItem(R.id.item_menuGames).getSubMenu().
                    add(gameEntity.getGame_entity_name().toLowerCase());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void logoutButtonClicked(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("logged", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        startActivity(new Intent(this, LoginActivity.class));

        finish();
    }
}

