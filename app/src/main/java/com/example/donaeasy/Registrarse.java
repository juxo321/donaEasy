package com.example.donaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;

import android.os.Bundle;

public class Registrarse extends AppCompatActivity {


    EditText txtApodo;
    EditText txtPassword;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        txtApodo = findViewById(R.id.txtApodo);
        txtPassword = findViewById(R.id.txtPassword);
    }

    public void AccionRegistrar(View view){

    }
}