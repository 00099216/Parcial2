package com.andres00099216.parcial2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.andres00099216.parcial2.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Andres on 16/6/2018.
 */

public class NoticiaActivity extends AppCompatActivity {
    TextView newsTitle, newsDescription, newsBody;
    ImageView newsPhoto;

    String title, description, image, body;

    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticia);

        Bundle arg;
        Intent intent = getIntent();
        arg = intent.getExtras();

        title = arg.getString("title");
        image = arg.getString("image");
        description = arg.getString("description");
        body = arg.getString("body");

        newsPhoto = findViewById(R.id.imageView_showNewsPicture);
        newsTitle = findViewById(R.id.textView_showNewsTitle);
        newsDescription = findViewById(R.id.textView_showNewsDescription);
        newsBody = findViewById(R.id.textView_showNewsBody);

        newsTitle.setText(title);
        Picasso.get().load(image).into(newsPhoto);
        newsDescription.setText(description);
        newsBody.setText(body);

        collapsingToolbarLayout = findViewById(R.id.collapsingToolBar_layout);
        toolbar = findViewById(R.id.toolBar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }
}
