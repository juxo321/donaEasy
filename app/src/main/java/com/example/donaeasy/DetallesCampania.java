package com.example.donaeasy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class DetallesCampania extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_campania);
    }

    public void AlertEliminarCampaña(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar campaña");
        builder.setMessage("¿Estás seguro de eliminar la campaña?");

        builder.setPositiveButton("Si", null);
        builder.setNegativeButton("No", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
