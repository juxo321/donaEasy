package com.example.donaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

    public void formularioCampania(View view){
        Intent intentGuardarCampania =new Intent(GenerarCampania.this, GuardarCampania.class);
        intentGuardarCampania.putExtra("paciente", paciente);
        startActivity(intentGuardarCampania);
    }

    public void modificarCampania(View view){
        Intent intentModificarCampania =new Intent(GenerarCampania.this, ModificarCampania.class);
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
                            paciente = null;
                            txtNombre.setText("Nombre: ");
                            txtTipoSangre.setText("Tipo de sangre: ");
                            txtDonadoresNecesarios.setText("Donadores requeridos: ");
                            txtUbicacion.setText("Ubicación: ");
                            txtDescripcion.setText("Descripción");
                            btnCrearCampania.setEnabled(true);
                            Toast.makeText(GenerarCampania.this, "Campaña eliminada correctamente", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_delete)
                    .show();
        }catch(Exception e){
            Toast.makeText(GenerarCampania.this, "Error al eliminar la campaña", Toast.LENGTH_SHORT).show();
        }

    }

    public void verificarEstadoDeLaCampania(View view){
        if(paciente != null){
            AlertDialog alertDialog = new AlertDialog.Builder(GenerarCampania.this).create();
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
            Toast.makeText(GenerarCampania.this, "No existe ninguna campaña", Toast.LENGTH_SHORT).show();
        }
    }

}