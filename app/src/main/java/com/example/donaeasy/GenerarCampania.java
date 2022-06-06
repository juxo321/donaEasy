package com.example.donaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GenerarCampania extends AppCompatActivity {

    Paciente paciente;

    boolean primerIngreso;


    Button btnCrearCampania ;
    TextView txtNombre;
    TextView txtTipoSangre;
    TextView txtDonadoresNecesarios;
    TextView txtUbicacion;
    TextView txtDescripcion;

    private DatabaseReference dbDonaEasy;


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

        dbDonaEasy = FirebaseDatabase.getInstance().getReference("DonaEasy");

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu3, menu);
        return true;
    }

    public boolean miCampania(MenuItem item){
        Intent intentMiCampania =new Intent(GenerarCampania.this, MiCampania.class);
        intentMiCampania.putExtra("paciente", paciente);
        startActivity(intentMiCampania);
        return true;
    }

    public void formularioCampania(View view){
        Intent intentGuardarCampania =new Intent(GenerarCampania.this, GuardarCampania.class);
        intentGuardarCampania.putExtra("paciente", paciente);
        startActivity(intentGuardarCampania);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //this.finish();
    }

    public void cerrarSesion(View view){
        Intent intentIniciarSesion =new Intent(GenerarCampania.this, IniciarSesion.class);
        startActivity(intentIniciarSesion);
        finish();
        Toast.makeText(GenerarCampania.this, "Hasta pronto...", Toast.LENGTH_SHORT).show();
    }

}