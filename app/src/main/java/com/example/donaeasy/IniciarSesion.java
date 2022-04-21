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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
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

    boolean banderaUsuarioExiste = false;

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

        dbDonaEasy = FirebaseDatabase.getInstance().getReference();
        Query query = dbDonaEasy.child("DonaEasy");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ((snapshot.exists())){
                    for (DataSnapshot usuarioBd: snapshot.getChildren()){
                        if(usuario.getUsuario().equals(usuarioBd.child("usuario").getValue().toString()) && usuario.getContrasena().equals(usuarioBd.child("contrasena").getValue().toString())){
                            usuario.setTipo(usuarioBd.child("tipo").getValue().toString());
                            banderaUsuarioExiste = true;
                            break;
                        }else{
                            banderaUsuarioExiste = false;
                        }
                    }
                }

                if(banderaUsuarioExiste){
                    if(usuario.getTipo().equals("Paciente")){
                        Toast.makeText(IniciarSesion.this, "Iniciando sesión...", Toast.LENGTH_SHORT).show();
                        Intent intentCrearCampania =new Intent(IniciarSesion.this, CrearCampania.class);
                        startActivity(intentCrearCampania);
                    }else {
                        Toast.makeText(IniciarSesion.this, "Iniciando sesión...", Toast.LENGTH_SHORT).show();
                        Intent intentCampaniasDisponibles =new Intent(IniciarSesion.this, CampaniasDisponibles.class);
                        startActivity(intentCampaniasDisponibles);
                    }
                }else {
                    Toast.makeText(IniciarSesion.this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }


}