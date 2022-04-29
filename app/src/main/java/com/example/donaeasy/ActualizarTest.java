package com.example.donaeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;

public class ActualizarTest extends AppCompatActivity {


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
        setContentView(R.layout.activity_actualizar_test);
    }
}