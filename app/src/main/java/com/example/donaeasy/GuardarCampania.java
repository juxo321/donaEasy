package com.example.donaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GuardarCampania extends AppCompatActivity {

    Paciente paciente;
    Campania campania;

    Button guardarCampania;
    TextView txtNombrePaciente;
    TextView txtDonadoresNecesarios;
    TextView txtTipoSangre;
    TextView txtUbicacion;
    TextView txtDescripcion;

    private DatabaseReference dbDonaEasy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_campania);

        guardarCampania = findViewById(R.id.btnGuardarCampania);
        txtNombrePaciente = findViewById(R.id.txtNombrePaciente);
        txtDonadoresNecesarios = findViewById(R.id.txtDonadoresNecesarios);
        txtTipoSangre = findViewById(R.id.txtTipoSangre);
        txtUbicacion = findViewById(R.id.txtUbicacion);
        txtDescripcion = findViewById(R.id.txtDescripcion);

        paciente =(Paciente) getIntent().getExtras().getSerializable("paciente");

        dbDonaEasy = FirebaseDatabase.getInstance().getReference("DonaEasy");
    }

    public void guardarCampania(View view){
        if(!txtNombrePaciente.getText().toString().equals("") && !txtDonadoresNecesarios.getText().toString().equals("") && !txtTipoSangre.getText().toString().equals("") && !txtUbicacion.getText().toString().equals("") && !txtDescripcion.getText().toString().equals("")){
            campania = new Campania(txtNombrePaciente.getText().toString(), Integer.parseInt(txtDonadoresNecesarios.getText().toString()), txtTipoSangre.getText().toString(), txtUbicacion.getText().toString(), txtDescripcion.getText().toString());
            dbDonaEasy.child("Paciente").child(paciente.getId()).child("campania").setValue(campania);
            paciente.setCampania(campania);
            Intent intentCampaniasDisponibles =new Intent(GuardarCampania.this, GenerarCampania.class);
            Toast.makeText(GuardarCampania.this, "Campaña creada correctamente", Toast.LENGTH_LONG).show();
            intentCampaniasDisponibles.putExtra("pacienteGuardar", paciente);
            startActivity(intentCampaniasDisponibles);
        }else {
            Toast.makeText(GuardarCampania.this, "Por favor conteste todas las preguntas", Toast.LENGTH_LONG).show();
        }
    }


}