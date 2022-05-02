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

public class ModificarCampania extends AppCompatActivity {

    Button btnGuardarCampania;
    TextView txtNombrePaciente;
    TextView txtTipoSangre;
    TextView txtDonadoresNecesarios;
    TextView txtUbicacion;
    TextView txtDescripcion;


    Paciente paciente;
    Campania campania;

    private DatabaseReference dbDonaEasy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_campania);

        btnGuardarCampania = findViewById(R.id.btnGuardarCampania);

        txtNombrePaciente = findViewById(R.id.txtNombrePaciente);
        txtTipoSangre = findViewById(R.id.txtTipoSangre);
        txtDonadoresNecesarios = findViewById(R.id.txtDonadoresNecesarios);
        txtUbicacion = findViewById(R.id.txtUbicacion);
        txtDescripcion = findViewById(R.id.txtDescripcion);

        paciente = (Paciente) getIntent().getExtras().getSerializable("pacienteModificar");
        dbDonaEasy = FirebaseDatabase.getInstance().getReference("DonaEasy");

        txtNombrePaciente.append(paciente.getCampania().getNombrePaciente());
        txtTipoSangre.append(paciente.getCampania().getTipoSangre());
        txtDonadoresNecesarios.append(String.valueOf(paciente.getCampania().getDonadoresNecesarios()));
        txtUbicacion.append(paciente.getCampania().getUbicacion());
        txtDescripcion.append(paciente.getCampania().getDescripcion());
    }

    public void guardarCampania(View view){
        if(!txtNombrePaciente.getText().toString().equals("") && !txtDonadoresNecesarios.getText().toString().equals("") && !txtTipoSangre.getText().toString().equals("") && !txtUbicacion.getText().toString().equals("") && !txtDescripcion.getText().toString().equals("")){
            try{
                campania = new Campania(txtNombrePaciente.getText().toString(), Integer.parseInt(txtDonadoresNecesarios.getText().toString()), txtTipoSangre.getText().toString(), txtUbicacion.getText().toString(), txtDescripcion.getText().toString());
                dbDonaEasy.child("Paciente").child(paciente.getId()).child("campania").setValue(campania);
                paciente.setCampania(campania);
                Intent intentCampaniasDisponibles =new Intent(ModificarCampania.this, GenerarCampania.class);
                Toast.makeText(ModificarCampania.this, "Campaña modificada correctamente", Toast.LENGTH_LONG).show();
                intentCampaniasDisponibles.putExtra("pacienteGuardar", paciente);
                startActivity(intentCampaniasDisponibles);
            }catch (Exception e){
                Toast.makeText(ModificarCampania.this, "Error al modificar campaña", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(ModificarCampania.this, "Por favor ingrese todo los campos", Toast.LENGTH_LONG).show();
        }
    }
}