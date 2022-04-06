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


    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);
        radioGroup = (RadioGroup) findViewById(R.id.radioGrupo);
        radiobtnPaciente = findViewById(R.id.RadioPaciente);
        radiobtnDonador = findViewById(R.id.RadioDonador);



    }

    public void Registrarse(View view){
        String tipo = "";

        if(radiobtnDonador.isChecked()){
            tipo = radiobtnDonador.getText().toString();
        }else if (radiobtnPaciente.isChecked()){
            tipo = radiobtnPaciente.getText().toString();
        }

        Map<String, Object> usuario = new HashMap<>();
        usuario.put("usuario", txtUsuario.getText().toString());
        usuario.put("contrasena", txtPassword.getText().toString());
        usuario.put("tipo", tipo);

        System.out.println(txtUsuario.toString());
        System.out.println(txtPassword.toString());
        System.out.println(tipo.toString());



        // Add a new document with a generated ID
        if(db.collection("usuarios").add(usuario).isSuccessful()){
            Toast.makeText(this, "Usuario agregado exitosamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Usuario agregado exitosamente", Toast.LENGTH_SHORT).show();
        }

        Intent intentIniciarSesion =new Intent(Registrarse.this, IniciarSesion.class);
        startActivity(intentIniciarSesion);
    }
}