package com.example.donaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MiCampania extends AppCompatActivity {

    Paciente paciente;
    Button btnEliminarCampania;
    Button btnModificarCampania;

    private DatabaseReference dbDonaEasy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_campania);

        btnEliminarCampania = findViewById(R.id.EliminarCampania);
        btnModificarCampania = findViewById(R.id.ModificarCampania);
        paciente = (Paciente) getIntent().getExtras().getSerializable("paciente");
        dbDonaEasy = FirebaseDatabase.getInstance().getReference("DonaEasy");

        if(paciente.getCampania() !=null){
            btnEliminarCampania.setEnabled(true);
            btnModificarCampania.setEnabled(true);
        }else {
            btnEliminarCampania.setEnabled(false);
            btnModificarCampania.setEnabled(false);
        }
    }

    public void modificarCampania(View view){
        Intent intentModificarCampania =new Intent(MiCampania.this, ModificarCampania.class);
        intentModificarCampania.putExtra("pacienteModificar", paciente);
        startActivity(intentModificarCampania);
    }



    public void eliminarCampania(View view){
        try {
            new AlertDialog.Builder(this)
                    .setTitle("Borrar Campaña")
                    .setMessage("¿Desea borrar la campaña?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dbDonaEasy.child("Paciente").child(paciente.getId()).child("campania").removeValue();
                            //txtNombre.setText("Nombre: ");
                            //txtTipoSangre.setText("Tipo de sangre: ");
                            //txtDonadoresNecesarios.setText("Donadores requeridos: ");
                            //txtUbicacion.setText("Ubicación: ");
                            //txtDescripcion.setText("Descripción");
                            //btnCrearCampania.setEnabled(true);
                            btnEliminarCampania.setEnabled(false);
                            btnModificarCampania.setEnabled(false);
                            //paciente.getCampania().setDonadoresNecesarios(-1);
                            paciente.setCampania(null);
                            Toast.makeText(MiCampania.this, "Campaña eliminada correctamente", Toast.LENGTH_SHORT).show();
                            Intent intentCampaniasDisponibles =new Intent(MiCampania.this, GenerarCampania.class);
                            intentCampaniasDisponibles.putExtra("pacienteGuardar", paciente);
                            startActivity(intentCampaniasDisponibles);
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_delete)
                    .show();
        }catch(Exception e){
            Toast.makeText(MiCampania.this, "Error al eliminar la campaña", Toast.LENGTH_SHORT).show();
        }

    }

    public void verificarEstadoDeLaCampania(View view){
        if(paciente.getCampania() != null){
            if(paciente.getCampania().getDonadoresNecesarios() != -1){
                AlertDialog alertDialog = new AlertDialog.Builder(MiCampania.this).create();
                alertDialog.setTitle("Estado de la campaña");
                alertDialog.setMessage("Donadores faltantes: "+ paciente.getCampania().getDonadoresNecesarios());
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }else {
                Toast.makeText(MiCampania.this, "No existe ninguna campaña", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(MiCampania.this, "No existe ninguna campaña", Toast.LENGTH_SHORT).show();
        }

    }



}