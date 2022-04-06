package com.example.donaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IniciarSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciarsesion);

    }

    public void acitivityRegistrarse(View view){
        Intent intentRegistrarse =new Intent(IniciarSesion.this, Registrarse.class);
        startActivity(intentRegistrarse);
    }
}