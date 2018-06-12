package com.andres00099216.parcial2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andres00099216.parcial2.API.Deserializadores.TokenDes;
import com.andres00099216.parcial2.API.NoticiasAPI;
import com.andres00099216.parcial2.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private Button boton;
    private EditText usuario, contraseña;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usuario = findViewById(R.id.username);
        contraseña = findViewById(R.id.contraseña);
        boton = findViewById(R.id.boton_entrar);

        boton.setOnClickListener(v -> Click());
    }

    public void Click() {
        if (usuario.getText().toString().equals("") || contraseña.getText().toString().equals("")) {
            Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show();

        } else {
            Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new TokenDes()).create();
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(NoticiasAPI.ENDPOINT).addConverterFactory(GsonConverterFactory.create(gson));
            Retrofit retrofit = builder.build();
            NoticiasAPI apiGameNews = retrofit.create(NoticiasAPI.class);

            Call<String> stringCall = apiGameNews.token(usuario.getText().toString(), usuario.getText().toString());

            stringCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful() && !response.body().equals("")) {

                        startMainActivity();
                    } else {
                        Toast.makeText(LoginActivity.this, "Error: check later", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {

                        Toast.makeText(LoginActivity.this, "Timed Out", Toast.LENGTH_SHORT).show();
                    } else if (t instanceof Exception) {
                        Toast.makeText(LoginActivity.this, "There was an error. \n Please try again later", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }


    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}