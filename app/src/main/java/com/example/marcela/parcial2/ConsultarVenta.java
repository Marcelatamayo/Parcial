package com.example.marcela.parcial2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.marcela.parcial2.helper.Ventas;
import com.example.marcela.parcial2.helper.helper;

import java.util.ArrayList;

public class ConsultarVenta extends AppCompatActivity {

    ListView listViewVentas;
    ArrayList<String> listaInformacion;
    ArrayList<Ventas> listaVentas;
    AyudaBD conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_venta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //this line shows back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Consultar Venta");
        getSupportActionBar().setElevation(0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        listViewVentas= (ListView) findViewById(R.id.listViewVentas);

        consultarListaClientes();

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewVentas.setAdapter(adaptador);
        listViewVentas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                Ventas ventas = listaVentas.get(pos);
                Intent intent=new Intent(ConsultarVenta.this,DetalleVenta.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("ventas",ventas);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    private void consultarListaClientes() {
        final AyudaBD ayudabd = new AyudaBD(getApplicationContext());
        SQLiteDatabase db = ayudabd.getWritableDatabase();
        Ventas ventas=null;
        listaVentas = new ArrayList<Ventas>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ helper.TABLA_VENTAS,null);

        while (cursor.moveToNext()){
            ventas = new Ventas();
            ventas.setCodVenta(cursor.getInt(0));
            ventas.setCodCliente(cursor.getInt(1));
            ventas.setTipoServicio(cursor.getString(2));
            ventas.setFecha(cursor.getString(3));
            ventas.setTotal(cursor.getInt(4));

            listaVentas.add(ventas);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i=0; i<listaVentas.size();i++){
            listaInformacion.add(listaVentas.get(i).getCodVenta()+" - "
                    +listaVentas.get(i).getCodCliente());
        }
    }
}
