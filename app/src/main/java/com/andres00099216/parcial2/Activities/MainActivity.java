package com.andres00099216.parcial2.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.andres00099216.parcial2.R;
import com.andres00099216.parcial2.db.db;
import com.andres00099216.parcial2.fragmentos.NoticiaFragmento;

public class MainActivity extends AppCompatActivity {
    db gameNewsDatabase;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameNewsDatabase = db.getInstance(getApplicationContext());


        setContentView(R.layout.main_activity);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initMain();

    }
    private void initMain() {
        Fragment fragment = new NoticiaFragmento();
        getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout, fragment).commit();
        getSupportActionBar().setTitle("Noticias");
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

