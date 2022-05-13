package com.example.donaeasy;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Registrarse extends AppCompatActivity {


    private EditText txtUsuario;
    private EditText txtPassword;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private  RadioButton radiobtnPaciente;
    private  RadioButton radiobtnDonador;

    private Paciente paciente;
    private Donador donador;

    private DatabaseReference dbDonaEasy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        txtUsuario = findViewById(R.id.txtUsuarioRegistrarse);
        txtPassword = findViewById(R.id.txtPasswordRegistrarse);
        radioGroup = (RadioGroup) findViewById(R.id.radioGrupo);
        radiobtnPaciente = findViewById(R.id.RadioPaciente);
        radiobtnDonador = findViewById(R.id.RadioDonador);

        dbDonaEasy = FirebaseDatabase.getInstance().getReference("DonaEasy");


    }

    public void Registrarse(View view){
        if(!txtUsuario.getText().toString().trim().equals("") && !txtPassword.getText().toString().trim().equals("")){
            String tipo = "";
            String usuario = txtUsuario.getText().toString();
            String contrasena = txtPassword.getText().toString();
            String id = dbDonaEasy.push().getKey();

            if(radiobtnDonador.isChecked()){
                donador = new Donador(false);
                tipo = radiobtnDonador.getText().toString();
                donador.setUsuario(usuario);
                donador.setContrasena(contrasena);
                donador.setCita(null);
                donador.setTipo(tipo);

            }else if (radiobtnPaciente.isChecked()){
                paciente = new Paciente(null);
                tipo = radiobtnPaciente.getText().toString();
                paciente.setUsuario(usuario);
                paciente.setContrasena(contrasena);
                paciente.setTipo(tipo);
            }

            // Add a new document with a generated ID
            if(!txtUsuario.getText().toString().trim().equals("") && !txtPassword.getText().toString().trim().equals("")){
                assert id != null;
                if(tipo.equals("Donador")){
                    try {
                        dbDonaEasy.child("Donador").child(id).setValue(donador);
                        Toast.makeText(this, "Usuario agregado exitosamente", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(this, "Error al agregar usuario", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    try {
                        dbDonaEasy.child("Paciente").child(id).setValue(paciente);
                        Toast.makeText(this, "Usuario agregado exitosamente", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(this, "Error al agregar usuario", Toast.LENGTH_SHORT).show();
                    }
                }
            }else {
                Toast.makeText(this, "Por favor rellene todos los campos", Toast.LENGTH_SHORT).show();
            }
            Intent intentIniciarSesion =new Intent(Registrarse.this, IniciarSesion.class);
            startActivity(intentIniciarSesion);
        }

    }
}