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

    Donador donador;
    Paciente paciente;

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

        donador = null;
        paciente = null;


        ArrayList<Usuario> lista = new ArrayList<>();

        String usuario = txtUsuario.getText().toString();
        String contrasena = txtContrasena.getText().toString();

        dbDonaEasy = FirebaseDatabase.getInstance().getReference();
        Query query = dbDonaEasy.child("DonaEasy");



        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ((snapshot.exists())){
                    for (DataSnapshot tipoUsuario: snapshot.getChildren()){
                        for (DataSnapshot usuarioBd: tipoUsuario.getChildren()) {
                            if (usuario.equals(usuarioBd.child("usuario").getValue().toString()) && contrasena.equals(usuarioBd.child("contrasena").getValue().toString())) {
                                if (usuarioBd.child("tipo").getValue().toString().equals("Donador")){
                                    donador = new Donador();
                                    donador.setUsuario(usuario);
                                    donador.setContrasena(contrasena);
                                    donador.setTipo(usuarioBd.child("tipo").getValue().toString());
                                    donador.setTestCompleto((Boolean) usuarioBd.child("testCompleto").getValue());
                                    donador.setCita((Cita) usuarioBd.child("cita").getValue(Cita.class));
                                    if (usuarioBd.child("estatus").getValue() == null){
                                        donador.setEstatus(null);
                                    }else {
                                        donador.setEstatus(usuarioBd.child("estatus").getValue().toString());
                                    }
                                    donador.setRespuestasTest((ArrayList<Boolean>) usuarioBd.child("respuestasTest").getValue());
                                    donador.setId(usuarioBd.getKey());
                                    banderaUsuarioExiste = true;
                                    break;
                                }else {
                                    paciente = new Paciente();
                                    paciente.setUsuario(usuario);
                                    paciente.setContrasena(contrasena);
                                    paciente.setTipo(usuarioBd.child("tipo").getValue().toString());
                                    paciente.setId(usuarioBd.getKey());
                                    paciente.setCampania((Campania) usuarioBd.child("campania").getValue(Campania.class));
                                    banderaUsuarioExiste = true;
                                    break;
                                }
                            } else {
                                banderaUsuarioExiste = false;
                            }
                        }
                        if(banderaUsuarioExiste){
                            break;
                        }
                    }
                }



                if(banderaUsuarioExiste){
                    if(paciente != null){
                        try{
                            Toast.makeText(IniciarSesion.this, "Iniciando sesi??n...", Toast.LENGTH_SHORT).show();
                            Intent intentGuardarCampania =new Intent(IniciarSesion.this, GenerarCampania.class);
                            intentGuardarCampania.putExtra("paciente",paciente);
                            startActivity(intentGuardarCampania);
                        }catch(Exception e){
                            Toast.makeText(IniciarSesion.this, "Fallo en la conexion con la base de datos", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        if(!donador.getTestCompleto()){
                            try {
                                Toast.makeText(IniciarSesion.this, "Iniciando sesi??n...", Toast.LENGTH_SHORT).show();
                                Intent intentTest =new Intent(IniciarSesion.this, Test.class);
                                intentTest.putExtra("donador", donador);
                                startActivity(intentTest);
                            }catch (Exception e){
                                Toast.makeText(IniciarSesion.this, "Fallo en la conexion con la base de datos", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            try {
                                Toast.makeText(IniciarSesion.this, "Iniciando sesi??n...", Toast.LENGTH_SHORT).show();
                                Intent intentCampaniasDisponibles =new Intent(IniciarSesion.this, RecuperarCampanias.class);
                                intentCampaniasDisponibles.putExtra("donador", donador);
                                startActivity(intentCampaniasDisponibles);
                            }catch (Exception e){
                                Toast.makeText(IniciarSesion.this, "Fallo en la conexion con la base de datos", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                }else {
                    Toast.makeText(IniciarSesion.this, "Usuario o contrase??a incorrectos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //this.finish();
    }


}