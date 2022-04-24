package com.example.donaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GenerarCampania extends AppCompatActivity {

    Button btnCrearCampania ;
    TextView txtNombre;
    TextView txtTipoSangre;
    TextView txtDonadoresRequeridos;
    TextView txtUbicacion;
    TextView txtDescripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_campania);

        btnCrearCampania = findViewById(R.id.btnCrearCampania);

        TextView txtNombre = findViewById(R.id.txtNombre);
        TextView txtTipoSangre = findViewById(R.id.txtTipoSangre);
        TextView txtDonadoresRequerid = findViewById(R.id.txtDonadoresRequeridos);
        TextView txtUbicacion = findViewById(R.id.txtUbicacion);
        TextView txtDescripcion = findViewById(R.id.txtDescripcion);

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