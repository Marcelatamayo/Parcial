package com.example.marcela.parcial2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcela.parcial2.helper.Ventas;
import com.example.marcela.parcial2.helper.helper;

public class DetalleVenta extends AppCompatActivity {

    TextView tvCodVenta, tvFechaVenta, tvValorVenta, tvClienteVenta, tvIdClienteVenta,
            tvTelClienteVenta, tvTipoServicio;

    Button btEliminarVenta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_venta);

        final AyudaBD ayudabd = new AyudaBD(getApplicationContext());

        tvCodVenta = (TextView) findViewById(R.id.tvCodVenta);
        tvFechaVenta = (TextView) findViewById(R.id.tvFechaVenta);
        tvValorVenta = (TextView) findViewById(R.id.tvValorVenta);
        tvClienteVenta = (TextView) findViewById(R.id.tvClienteVenta);
        tvIdClienteVenta = (TextView) findViewById(R.id.tvIdClienteVenta);
        tvTelClienteVenta = (TextView) findViewById(R.id.tvTelClienteVenta);
        tvTipoServicio = (TextView) findViewById(R.id.tvTipoServicio);

        btEliminarVenta = (Button) findViewById(R.id.btEliminarVenta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //this line shows back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detalle Venta");
        getSupportActionBar().setElevation(0);

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        Bundle objetoEnviado = getIntent().getExtras();
        Ventas venta = null;

        if(objetoEnviado!=null){
            venta= (Ventas) objetoEnviado.getSerializable("ventas");
            tvCodVenta.setText(venta.getCodVenta().toString());
            tvTipoServicio.setText(venta.getTipoServicio().toString());
            tvFechaVenta.setText(venta.getFecha().toString());
            tvValorVenta.setText(venta.getTotal().toString());
            consultarCliente(venta.getCodCliente());
        }

        btEliminarVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = ayudabd.getWritableDatabase();
                String[] argsel = {tvCodVenta.getText().toString()};
                db.delete(helper.TABLA_VENTAS,helper.COLUMNA_CODVENTA+"=?",argsel);
                Toast.makeText(getApplicationContext(),"Venta Eliminada",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), GestionVentas.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void consultarCliente(Integer codCliente) {
        final AyudaBD ayudabd = new AyudaBD(getApplicationContext());
        SQLiteDatabase db = ayudabd.getReadableDatabase();//DECLARAR LA BD COMO LECTURA
        String[] argsel = {codCliente.toString()};//ARGUMENTO DE SELECCION
        String[] campos={helper.COLUMNA_NOMBRES,helper.COLUMNA_TELEFONOS};

        try {

            Cursor c = db.query(helper.TABLA_CLIENTES,campos,helper.COLUMNA_ID+"=?",argsel,
                    null, null, null);
            c.moveToFirst();
            tvIdClienteVenta.setText(codCliente.toString());
            tvClienteVenta.setText(c.getString(0));
            tvTelClienteVenta.setText(c.getString(1));
            c.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            tvClienteVenta.setText("");
            tvTelClienteVenta.setText("");

        }



    }
}
