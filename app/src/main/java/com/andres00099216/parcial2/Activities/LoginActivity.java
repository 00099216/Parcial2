package com.andres00099216.parcial2.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andres00099216.parcial2.R;

public class LoginActivity extends AppCompatActivity {
    private Button boton;
    private EditText usuario, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usuario = findViewById(R.id.username);
        contraseña =findViewById(R.id.contraseña);
        boton = findViewById(R.id.boton_entrar);

        boton.setOnClickListener(v -> Click());
    }

    public void Click(){
        if (usuario.getText().toString().equals("") || contraseña.getText().toString().equals("")){
            Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show();

        }else {

        }

    }
}