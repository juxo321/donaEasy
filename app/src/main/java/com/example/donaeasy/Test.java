package com.example.donaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Test extends AppCompatActivity {

    Donador usuarioLogueado;

    private DatabaseReference dbDonaEasy;

    String id;

    RadioGroup radioGrupoPregunta1;
    RadioButton radioPregunta1OpcionSi;
    RadioButton radioPregunta1OpcionNo ;

    RadioGroup radioGrupoPregunta2;
    RadioButton radioPregunta2OpcionSi;
    RadioButton radioPregunta2OpcionNo;

    RadioGroup radioGrupoPregunta3;
    RadioButton radioPregunta3OpcionSi;
    RadioButton radioPregunta3OpcionNo;

    RadioGroup radioGrupoPregunta4;
    RadioButton radioPregunta4OpcionSi;
    RadioButton radioPregunta4OpcionNo;

    RadioGroup radioGrupoPregunta5;
    RadioButton radioPregunta5OpcionSi;
    RadioButton radioPregunta5OpcionNo;

    RadioGroup radioGrupoPregunta6;
    RadioButton radioPregunta6OpcionSi;
    RadioButton radioPregunta6OpcionNo;

    RadioGroup radioGrupoPregunta7;
    RadioButton radioPregunta7OpcionSi;
    RadioButton radioPregunta7OpcionNo;

    RadioGroup radioGrupoPregunta8;
    RadioButton radioPregunta8OpcionSi;
    RadioButton radioPregunta8OpcionNo;

    RadioGroup radioGrupoPregunta9;
    RadioButton radioPregunta9OpcionSi;
    RadioButton radioPregunta9OpcionNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        usuarioLogueado = (Donador) getIntent().getExtras().getSerializable("donador");

        dbDonaEasy = FirebaseDatabase.getInstance().getReference("DonaEasy");

        id = usuarioLogueado.getId();

        radioGrupoPregunta1 = (RadioGroup) findViewById(R.id.radioGrupoPregunta1);
        radioPregunta1OpcionSi = findViewById(R.id.radioPregunta1OpcionSi);
        radioPregunta1OpcionNo = findViewById(R.id.radioPregunta1OpcionNo);

        radioGrupoPregunta2 = (RadioGroup) findViewById(R.id.radioGrupoPregunta2);
        radioPregunta2OpcionSi = findViewById(R.id.radioPregunta2OpcionSi);
        radioPregunta2OpcionNo = findViewById(R.id.radioPregunta2OpcionNo);

        radioGrupoPregunta3 = (RadioGroup) findViewById(R.id.radioGrupoPregunta3);
        radioPregunta3OpcionSi = findViewById(R.id.radioPregunta3OpcionSi);
        radioPregunta3OpcionNo = findViewById(R.id.radioPregunta3OpcionNo);

        radioGrupoPregunta4 = (RadioGroup) findViewById(R.id.radioGrupoPregunta4);
        radioPregunta4OpcionSi = findViewById(R.id.radioPregunta4OpcionSi);
        radioPregunta4OpcionNo = findViewById(R.id.radioPregunta4OpcionNo);

        radioGrupoPregunta5 = (RadioGroup) findViewById(R.id.radioGrupoPregunta5);
        radioPregunta5OpcionSi = findViewById(R.id.radioPregunta5OpcionSi);
        radioPregunta5OpcionNo = findViewById(R.id.radioPregunta5OpcionNo);

        radioGrupoPregunta6 = (RadioGroup) findViewById(R.id.radioGrupoPregunta6);
        radioPregunta6OpcionSi = findViewById(R.id.radioPregunta6OpcionSi);
        radioPregunta6OpcionNo = findViewById(R.id.radioPregunta6OpcionNo);

        radioGrupoPregunta7 = (RadioGroup) findViewById(R.id.radioGrupoPregunta7);
        radioPregunta7OpcionSi = findViewById(R.id.radioPregunta7OpcionSi);
        radioPregunta7OpcionNo = findViewById(R.id.radioPregunta7OpcionNo);

        radioGrupoPregunta8 = (RadioGroup) findViewById(R.id.radioGrupoPregunta8);
        radioPregunta8OpcionSi = findViewById(R.id.radioPregunta8OpcionSi);
        radioPregunta8OpcionNo = findViewById(R.id.radioPregunta8OpcionNo);

        radioGrupoPregunta9 = (RadioGroup) findViewById(R.id.radioGrupoPregunta9);
        radioPregunta9OpcionSi = findViewById(R.id.radioPregunta9OpcionSi);
        radioPregunta9OpcionNo = findViewById(R.id.radioPregunta9OpcionNo);
    }

    public void enviarTest(View view){
        ArrayList<Boolean> listaRespuestasTest = new ArrayList<>();


        if(radioPregunta1OpcionSi.isChecked()){
            listaRespuestasTest.add(radioPregunta1OpcionSi.isChecked());
        }else if (radioPregunta1OpcionNo.isChecked()){
            listaRespuestasTest.add(false);
        }

        if(radioPregunta2OpcionSi.isChecked()){
            listaRespuestasTest.add(radioPregunta2OpcionSi.isChecked());
        }else if (radioPregunta2OpcionNo.isChecked()){
            listaRespuestasTest.add(false);
        }

        if(radioPregunta3OpcionSi.isChecked()){
            listaRespuestasTest.add(radioPregunta3OpcionSi.isChecked());
        }else if (radioPregunta3OpcionNo.isChecked()){
            listaRespuestasTest.add(false);
        }

        if(radioPregunta4OpcionSi.isChecked()){
            listaRespuestasTest.add(radioPregunta4OpcionSi.isChecked());
        }else if (radioPregunta4OpcionNo.isChecked()){
            listaRespuestasTest.add(false);
        }

        if(radioPregunta5OpcionSi.isChecked()){
            listaRespuestasTest.add(radioPregunta5OpcionSi.isChecked());
        }else if (radioPregunta5OpcionNo.isChecked()){
            listaRespuestasTest.add(false);
        }

        if(radioPregunta6OpcionSi.isChecked()){
            listaRespuestasTest.add(radioPregunta6OpcionSi.isChecked());
        }else if (radioPregunta6OpcionNo.isChecked()){
            listaRespuestasTest.add(false);
        }

        if(radioPregunta7OpcionSi.isChecked()){
            listaRespuestasTest.add(radioPregunta7OpcionSi.isChecked());
        }else if (radioPregunta7OpcionNo.isChecked()){
            listaRespuestasTest.add(false);
        }

        if(radioPregunta8OpcionSi.isChecked()){
            listaRespuestasTest.add(radioPregunta8OpcionSi.isChecked());
        }else if (radioPregunta8OpcionNo.isChecked()){
            listaRespuestasTest.add(false);
        }

        if(radioPregunta9OpcionSi.isChecked()){
            listaRespuestasTest.add(radioPregunta9OpcionSi.isChecked());
        }else if (radioPregunta9OpcionNo.isChecked()){
            listaRespuestasTest.add(false);
        }

        if((!radioPregunta1OpcionSi.isChecked() && !radioPregunta1OpcionNo.isChecked()) || (!radioPregunta2OpcionSi.isChecked() && !radioPregunta2OpcionNo.isChecked()) || (!radioPregunta3OpcionSi.isChecked() && !radioPregunta3OpcionNo.isChecked())
                || (!radioPregunta4OpcionSi.isChecked() && !radioPregunta4OpcionNo.isChecked()) || (!radioPregunta5OpcionSi.isChecked() && !radioPregunta5OpcionNo.isChecked()) || (!radioPregunta6OpcionSi.isChecked() && !radioPregunta6OpcionNo.isChecked())
            || (!radioPregunta7OpcionSi.isChecked() && !radioPregunta7OpcionNo.isChecked() || (!radioPregunta8OpcionSi.isChecked() && !radioPregunta8OpcionNo.isChecked()) || (!radioPregunta9OpcionSi.isChecked() && !radioPregunta9OpcionNo.isChecked()))){
            Toast.makeText(Test.this, "Por favor conteste todas las preguntas", Toast.LENGTH_LONG).show();
        }else {
            try {
                dbDonaEasy.child("Donador").child(id).child("respuestasTest").setValue(listaRespuestasTest);
                dbDonaEasy.child("Donador").child(id).child("testCompleto").setValue(true);
                Intent intentRecuperarCampanias =new Intent(Test.this, RecuperarCampanias.class);
                intentRecuperarCampanias.putExtra("donador", usuarioLogueado);
                startActivity(intentRecuperarCampanias);
            }catch (Exception e){
                Toast.makeText(Test.this, "Error al conectarse con la base de datos", Toast.LENGTH_SHORT).show();
            }
        }







    }

}