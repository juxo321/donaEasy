package com.example.donaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MiPerfil extends AppCompatActivity {

    Donador donador;

    TextView txtNombreDonador;

    DatabaseReference dbDonaEasy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);

        dbDonaEasy = FirebaseDatabase.getInstance().getReference("DonaEasy");

        if (getIntent().getSerializableExtra("donadorTest") != null) {
            donador = (Donador) getIntent().getSerializableExtra("donadorTest");
        }else if (getIntent().getSerializableExtra("donador") != null){
            donador = (Donador) getIntent().getSerializableExtra("donador");
        }

        txtNombreDonador = findViewById(R.id.txtNombreDonador);

        txtNombreDonador.append(" "+donador.getUsuario());


    }

    public void modificarTest(View view){
        Intent intentPerfil =new Intent(MiPerfil.this, ActualizarTest.class);
        intentPerfil.putExtra("donador", donador);
        startActivity(intentPerfil);
    }


}