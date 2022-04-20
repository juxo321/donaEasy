package com.example.donaeasy;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class IniciarSesion extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtContrasena;

    private DatabaseReference dbDonaEasy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciarsesion);

        txtUsuario = findViewById(R.id.txtUsuarioInicioSesion);
        txtContrasena = findViewById(R.id.txtContrasenaInicioSesion);

        dbDonaEasy = FirebaseDatabase.getInstance().getReference("DonaEasy");

    }

    public void acitivityRegistrarse(View view){
        Intent intentRegistrarse =new Intent(IniciarSesion.this, Registrarse.class);
        startActivity(intentRegistrarse);
    }

    public void iniciarSesion(View view){
        Usuario usuario = new Usuario();
        ArrayList<Usuario> lista = new ArrayList<>();

        usuario.setUsuario(txtUsuario.getText().toString());
        usuario.setContrasena(txtContrasena.getText().toString());

        //dbDonaEasy.
    });
    }


}