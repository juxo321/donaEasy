package com.example.donaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

    private DatabaseReference dbDonaEasy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_campanias);

        dbDonaEasy = FirebaseDatabase.getInstance().getReference("DonaEasy");

        inicializarElementos();


    }

    private void inicializarElementos() {
        recyclerCampanias = findViewById(R.id.recyclerViewCampanias);
        recyclerCampanias.setLayoutManager(new LinearLayoutManager(this));

        List<Campania> listaCampanias = new ArrayList<>();
        dbDonaEasy = FirebaseDatabase.getInstance().getReference();
        Query query = dbDonaEasy.child("DonaEasy").child("Paciente");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot paciente: snapshot.getChildren()) {
                        if(paciente.child("campania").getValue(Campania.class) != null){
                            campania = (Campania) paciente.child("campania").getValue(Campania.class);
                            listaCampanias.add(campania);
                        }
                    }
                }

                campaniasAdaptador = new CampaniaAdaptador(listaCampanias, RecuperarCampanias.this);
                recyclerCampanias.setAdapter(campaniasAdaptador);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}