package com.andres00099216.parcial2.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

import static android.view.View.GONE;

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
            NoticiasAPI noticiasApi = retrofit.create(NoticiasAPI.class);

            Call<String> stringCall = noticiasApi.token(usuario.getText().toString(), contraseña.getText().toString());
            stringCall.enqueue(new Callback <String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    System.out.println(response.code());
                    if (response.code() == 200) {
                        Toast.makeText(LoginActivity.this, "Exito", Toast.LENGTH_SHORT).show();
                        TokenGuardado(response.body());
                        startMainActivity();
                    }else if (response.code() == 401){

                        Toast.makeText(LoginActivity.this, "Error: wrong credentials", Toast.LENGTH_SHORT).show();
                    }
                else {

                    Toast.makeText(LoginActivity.this, "Error: check later", Toast.LENGTH_SHORT).show();
                }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        Toast.makeText(LoginActivity.this, "Time out", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }    private void TokenGuardado(String token) {
        SharedPreferences preferences = this.getSharedPreferences("logged", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.commit();
    }


    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
