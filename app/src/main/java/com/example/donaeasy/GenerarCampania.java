package com.example.donaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GenerarCampania extends AppCompatActivity {

    Button btnCrearCampania ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_campania);

        btnCrearCampania = findViewById(R.id.btnCrearCampania);

        Paciente paciente = (Paciente) getIntent().getExtras().getSerializable("paciente");
        if(paciente.getCampania() !=null){
            btnCrearCampania.setEnabled(false);
        }


    }

    public void formularioCampania(View view){
        Intent intentGuardarCampania =new Intent(GenerarCampania.this, GuardarCampania.class);
        startActivity(intentGuardarCampania);
    }


}