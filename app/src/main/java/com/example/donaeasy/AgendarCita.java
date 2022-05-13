package com.example.donaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AgendarCita extends AppCompatActivity {

    private Spinner spinnerHora;
    private CalendarView calendario;
    private TextView txtUbicacion;
    private String txtHora;
    private String txtFecha;
    private static final String[] paths = {"7:00 am", "8:00 am", "9:00 am", "10:00 am"};
    private Campania campaniaDonar;
    private Donador donador;

    private DatabaseReference dbDonaEasy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_cita);

        spinnerHora = (Spinner)findViewById(R.id.spinnerHora);
        calendario = (CalendarView) findViewById(R.id.calendario);
        txtUbicacion = (TextView) findViewById(R.id.txtUbicacion);
        campaniaDonar = (Campania) getIntent().getExtras().getSerializable("campaniaDonar");
        donador = (Donador) getIntent().getExtras().get("donador");
        txtUbicacion.append(campaniaDonar.getUbicacion());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AgendarCita.this, android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHora.setAdapter(adapter);

        dbDonaEasy = FirebaseDatabase.getInstance().getReference("DonaEasy");

        txtHora = spinnerHora.getSelectedItem().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtFecha = sdf.format(new Date(calendario.getDate()));


    }

    public void Donar(View view){
        txtHora = spinnerHora.getSelectedItem().toString();
        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                txtFecha = sdf.format(new Date(calendario.getDate()));
            }
        });

        if(txtHora.trim().equals("")){
            Toast.makeText(AgendarCita.this, "Por favor seleccione una hora", Toast.LENGTH_SHORT).show();
        }else{
            if(txtFecha.trim().equals("")){
                Toast.makeText(AgendarCita.this, "Por favor seleccione una fecha", Toast.LENGTH_SHORT).show();
            }else {
                try {
                    Cita cita = new Cita(txtFecha,txtHora);
                    dbDonaEasy.child("Donador").child(donador.getId()).child("cita").setValue(cita);
                    campaniaDonar.setDonadoresNecesarios(campaniaDonar.getDonadoresNecesarios()-1);
                    dbDonaEasy.child("Paciente").child(campaniaDonar.getIdPaciente()).child("campania").setValue(campaniaDonar);
                    Toast.makeText(AgendarCita.this, "Cita creada correctamente", Toast.LENGTH_SHORT).show();
                    donador.setCita(cita);
                    Intent intentCampaniasDisponibles =new Intent(AgendarCita.this, RecuperarCampanias.class);
                    intentCampaniasDisponibles.putExtra("donador", donador);
                    startActivity(intentCampaniasDisponibles);
                }catch (Exception e){
                    Toast.makeText(AgendarCita.this, "Fallo en la conexion con la base de datos", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }


}