package com.example.marcela.parcial2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    Button btGestionClientes, btGestionVentas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGestionClientes = (Button) findViewById(R.id.btGestionClientes);
        btGestionVentas = (Button) findViewById(R.id.btGestionVentas);

        btGestionClientes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), GestionClientes.class);
                startActivity(intent);
            }
        });

        btGestionVentas.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), GestionVentas.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId()){
            case R.id.cerrar_sesion:
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }
}
