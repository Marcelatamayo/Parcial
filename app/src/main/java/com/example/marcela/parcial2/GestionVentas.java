package com.example.marcela.parcial2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class GestionVentas extends AppCompatActivity {

    Button btNueva, btConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_ventas);

        btNueva = (Button) findViewById(R.id.btNueva);
        btConsultar = (Button) findViewById(R.id.btConsultar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //this line shows back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Gestión Ventas");
        getSupportActionBar().setElevation(0);

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        btNueva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NuevaVenta.class);
                startActivity(intent);
            }
        });

        btConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConsultarVenta.class);
                startActivity(intent);
            }
        });


    }
}
