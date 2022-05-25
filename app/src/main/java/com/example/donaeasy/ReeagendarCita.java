package com.example.donaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReeagendarCita extends AppCompatActivity {

    private Spinner spinnerHora;
    private CalendarView calendario;
    private TextView txtUbicacion;
    private String txtHora;
    private String txtFecha;
    private static final String[] paths = {"7:00 am", "8:00 am", "9:00 am", "10:00 am"};
    private Donador donador;

    private DatabaseReference dbDonaEasy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reeagendar_cita);

        spinnerHora = (Spinner)findViewById(R.id.spinnerHora);
        calendario = (CalendarView) findViewById(R.id.calendario);
        txtUbicacion = (TextView) findViewById(R.id.txtUbicacion);
        donador = (Donador) getIntent().getExtras().get("donador");
        txtUbicacion.append(donador.getCita().getCampaniaCita().getUbicacion());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReeagendarCita.this, android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHora.setAdapter(adapter);

        dbDonaEasy = FirebaseDatabase.getInstance().getReference("DonaEasy");
        calendario.setMinDate(System.currentTimeMillis() +24*60*60*1000);
        try {
            calendario.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(donador.getCita().getFecha()).getTime(), true, true);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i=0;i<4;i++) {
            if(paths[i].equals(donador.getCita().getHora())){
                spinnerHora.setSelection(i);
            }
        }
        txtHora = spinnerHora.getSelectedItem().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtFecha = sdf.format(new Date(calendario.getDate()));

        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                i1++;
                String dateString =(i2+"/"+i1+"/"+i);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaActualizada = new Date();
                try {
                    fechaActualizada = sdf.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                txtFecha = sdf.format(fechaActualizada);
            }
        });


    }

    public void  ReeagendarCita(View view){
        txtHora = spinnerHora.getSelectedItem().toString();

        if(txtHora.trim().equals("")){
            Toast.makeText(ReeagendarCita.this, "Por favor seleccione una hora", Toast.LENGTH_SHORT).show();
        }else{
            if(txtFecha.trim().equals("")){
                Toast.makeText(ReeagendarCita.this, "Por favor seleccione una fecha", Toast.LENGTH_SHORT).show();
            }else {
                try {
                    Cita cita = new Cita(txtFecha,txtHora, donador.getCita().getCampaniaCita());
                    dbDonaEasy.child("Donador").child(donador.getId()).child("cita").setValue(cita);
                    Toast.makeText(ReeagendarCita.this, "Cita actualizada correctamente", Toast.LENGTH_SHORT).show();
                    donador.setCita(cita);
                    Intent intentCampaniasDisponibles =new Intent(ReeagendarCita.this, RecuperarCampanias.class);
                    intentCampaniasDisponibles.putExtra("donador", donador);
                    startActivity(intentCampaniasDisponibles);
                    finish();
                }catch (Exception e){
                    Toast.makeText(ReeagendarCita.this, "Error al re-agendar cita", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}