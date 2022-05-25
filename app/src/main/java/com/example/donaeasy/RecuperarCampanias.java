package com.example.donaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecuperarCampanias extends AppCompatActivity {

    RecyclerView recyclerCampanias;
    CampaniaAdaptador campaniasAdaptador;
    Campania campania;
    Donador donador;
    List<Campania> listaCampanias;

    private DatabaseReference dbDonaEasy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_campanias);

        dbDonaEasy = FirebaseDatabase.getInstance().getReference("DonaEasy");

        inicializarElementos();

        donador = (Donador) getIntent().getExtras().getSerializable("donador");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void inicializarElementos() {
        recyclerCampanias = findViewById(R.id.recyclerViewCampanias);
        recyclerCampanias.setLayoutManager(new LinearLayoutManager(this));

        try {
            listaCampanias = new ArrayList<>();
            dbDonaEasy = FirebaseDatabase.getInstance().getReference();
            Query query = dbDonaEasy.child("DonaEasy").child("Paciente");


            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        for (DataSnapshot paciente: snapshot.getChildren()) {
                            if(paciente.child("campania").getValue(Campania.class) != null){
                                campania = (Campania) paciente.child("campania").getValue(Campania.class);
                                if(campania.getDonadoresNecesarios()>0){
                                    listaCampanias.add(campania);
                                }
                            }
                        }
                    }

                    campaniasAdaptador = new CampaniaAdaptador(listaCampanias, RecuperarCampanias.this, donador);
                    recyclerCampanias.setAdapter(campaniasAdaptador);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }catch (Exception e){
            Toast.makeText(RecuperarCampanias.this, "Fallo en la conexion con la base de datos", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean perfil(MenuItem item){
        Intent intentPerfil =new Intent(RecuperarCampanias.this, MiPerfil.class);
        intentPerfil.putExtra("donador", donador);
        intentPerfil.putExtra("numero", 1);
        startActivity(intentPerfil);
        return true;
    }

    public void compartirCampania(View view){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/DonaEasy?hl=es_MX&gl=US.");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //this.finish();
    }


}