package com.example.donaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    Button btnCancelarCita;
    Button btnReeagendarCita;
    EditText txtEstatus;

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
        btnCancelarCita = findViewById(R.id.btnCancelarCita);
        btnReeagendarCita = findViewById(R.id.btnReAgendarCita);
        txtEstatus = findViewById(R.id.txtEstatus);


        txtNombreDonador.append(" "+donador.getUsuario());

        if(donador.getCita()==null){
            btnCancelarCita.setEnabled(false);
            btnReeagendarCita.setEnabled(false);
        }

        if(donador.getEstatus().equals("Activo")){
            txtEstatus.setText("Activo");
            txtEstatus.setBackgroundColor(Color.GREEN);
        }else {
            txtEstatus.setText("Inactivo");
            txtEstatus.setBackgroundColor(Color.RED);
        }

        if(donador.getCita() != null && donador.getEstatus().equals("Inactivo")){
            dbDonaEasy.child("Donador").child(donador.getId()).child("cita").removeValue();
            donador.getCita().getCampaniaCita().setDonadoresNecesarios(donador.getCita().getCampaniaCita().getDonadoresNecesarios()+1);
            dbDonaEasy.child("Paciente").child(donador.getCita().getCampaniaCita().getIdPaciente()).child("campania").setValue(donador.getCita().getCampaniaCita());
            donador.setCita(null);
            Toast.makeText(MiPerfil.this, "Su cita ha sido cancelada debido a que no cumple con los requisitos para donar", Toast.LENGTH_SHORT).show();
            btnCancelarCita.setEnabled(false);
            btnReeagendarCita.setEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    public void modificarTest(View view){
        Intent intentPerfil =new Intent(MiPerfil.this, ActualizarTest.class);
        intentPerfil.putExtra("donador", donador);
        startActivity(intentPerfil);
        finish();
    }

    public void cancelarCita(View view){
        try {
            new AlertDialog.Builder(this)
                    .setTitle("Cancelar cita")
                    .setMessage("¿Desea cancelar la cita?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dbDonaEasy.child("Donador").child(donador.getId()).child("cita").removeValue();
                            donador.getCita().getCampaniaCita().setDonadoresNecesarios(donador.getCita().getCampaniaCita().getDonadoresNecesarios()+1);
                            dbDonaEasy.child("Paciente").child(donador.getCita().getCampaniaCita().getIdPaciente()).child("campania").setValue(donador.getCita().getCampaniaCita());
                            donador.setCita(null);
                            Toast.makeText(MiPerfil.this, "Cita cancelada correctamente", Toast.LENGTH_SHORT).show();
                            Intent intentRecuperarCampanias =new Intent(MiPerfil.this, RecuperarCampanias.class);
                            intentRecuperarCampanias.putExtra("donador", donador);
                            startActivity(intentRecuperarCampanias);
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_delete)
                    .show();
        }catch(Exception e){
            Toast.makeText(MiPerfil.this, "Error al cancelar la cita", Toast.LENGTH_SHORT).show();
        }
    }

    public void reeagendarCita(View view){
        Intent intentReeagendarCita =new Intent(MiPerfil.this, ReeagendarCita.class);
        intentReeagendarCita.putExtra("donador", donador);
        startActivity(intentReeagendarCita);
        finish();
    }

    public void cerrarSesion(View view){
        Intent intentIniciarSesion =new Intent(MiPerfil.this, IniciarSesion.class);
        startActivity(intentIniciarSesion);
        finish();
        Toast.makeText(MiPerfil.this, "Hasta pronto...", Toast.LENGTH_SHORT).show();
    }

    public boolean verCampanias(MenuItem item){
        Intent intentCampanias =new Intent(MiPerfil.this, RecuperarCampanias.class);
        intentCampanias.putExtra("donador", donador);
        startActivity(intentCampanias);
        finish();
        return true;
    }



    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //this.finish();
    }




}