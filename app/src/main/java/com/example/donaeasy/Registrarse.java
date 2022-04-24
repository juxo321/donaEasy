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

    private DatabaseReference dbDonaEasy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);
        radioGroup = (RadioGroup) findViewById(R.id.radioGrupo);
        radiobtnPaciente = findViewById(R.id.RadioPaciente);
        radiobtnDonador = findViewById(R.id.RadioDonador);

        dbDonaEasy = FirebaseDatabase.getInstance().getReference("DonaEasy");


    }

    public void Registrarse(View view){
        String tipo = "";
        String usuario = txtUsuario.getText().toString();
        String contrasena = txtPassword.getText().toString();
        String id = dbDonaEasy.push().getKey();

        if(radiobtnDonador.isChecked()){
            tipo = radiobtnDonador.getText().toString();
        }else if (radiobtnPaciente.isChecked()){
            tipo = radiobtnPaciente.getText().toString();
        }

        Usuario usuarioInsertar = new Usuario(false);
        usuarioInsertar.setUsuario(usuario);
        usuarioInsertar.setContrasena(contrasena);
        usuarioInsertar.setTipo(tipo);

        // Add a new document with a generated ID
        assert id != null;
        if(usuarioInsertar.getTipo().equals("Donador")){
            if(dbDonaEasy.child("Donador").child(id).setValue(usuarioInsertar).isSuccessful()){
                Toast.makeText(this, "Usuario agregado exitosamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Error al agregar usuario", Toast.LENGTH_SHORT).show();
            }
        }else{
            if(dbDonaEasy.child("Paciente").child(id).setValue(usuarioInsertar).isSuccessful()){
                Toast.makeText(this, "Usuario agregado exitosamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Error al agregar usuario", Toast.LENGTH_SHORT).show();
            }
        }


        Intent intentIniciarSesion =new Intent(Registrarse.this, IniciarSesion.class);
        startActivity(intentIniciarSesion);
    }
}