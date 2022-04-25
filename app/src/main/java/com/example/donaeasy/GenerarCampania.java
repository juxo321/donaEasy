package com.example.donaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GenerarCampania extends AppCompatActivity {

    Paciente paciente;

    boolean primerIngreso;


    Button btnCrearCampania ;
    TextView txtNombre;
    TextView txtTipoSangre;
    TextView txtDonadoresNecesarios;
    TextView txtUbicacion;
    TextView txtDescripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_campania);

        btnCrearCampania = findViewById(R.id.btnCrearCampania);

        txtNombre = findViewById(R.id.txtNombre);
        txtTipoSangre = findViewById(R.id.txtTipoSangre);
        txtDonadoresNecesarios = findViewById(R.id.txtDonadoresNecesarios);
        txtUbicacion = findViewById(R.id.txtUbicacion);
        txtDescripcion = findViewById(R.id.txtDescripcion);

        if(getIntent().getExtras().getSerializable("paciente") !=null) {
            paciente = (Paciente) getIntent().getExtras().getSerializable("paciente");
        }else if(getIntent().getExtras().getSerializable("pacienteGuardar") !=null){
            paciente = (Paciente) getIntent().getExtras().getSerializable("pacienteGuardar");
        }



        if(paciente.getCampania() !=null){
            btnCrearCampania.setEnabled(false);
            txtNombre.append(paciente.getCampania().getNombrePaciente());
            txtTipoSangre.append(paciente.getCampania().getTipoSangre());
            txtDonadoresNecesarios.append(String.valueOf(paciente.getCampania().getDonadoresNecesarios()));
            txtUbicacion.append(paciente.getCampania().getUbicacion());
            txtDescripcion.append(paciente.getCampania().getDescripcion());
        }

    }

    public void formularioCampania(View view){
        Intent intentGuardarCampania =new Intent(GenerarCampania.this, GuardarCampania.class);
        intentGuardarCampania.putExtra("paciente", paciente);
        startActivity(intentGuardarCampania);
    }


}