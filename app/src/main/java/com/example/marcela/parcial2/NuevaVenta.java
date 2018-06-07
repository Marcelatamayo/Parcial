package com.example.marcela.parcial2;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcela.parcial2.helper.Clientes;
import com.example.marcela.parcial2.helper.helper;

import java.util.ArrayList;
import java.util.Calendar;

public class NuevaVenta extends AppCompatActivity {

    ArrayList<String> listaClientes;
    ArrayList<Clientes> clientesList;
    Spinner spinnerClientes;

    RadioButton rbMinutos, rbAlquiler;
    String TipoServicio = "", Fecha = "", CodVenta = "", Total = "", Cliente = "";
    Button btFecha, btRegistrarVenta;
    TextView tvValorFecha;
    Calendar mCurrentDate;
    EditText etCodVenta, etTotal;

    int dia, mes, ano;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_venta);


        final AyudaBD ayudabd = new AyudaBD(getApplicationContext());

        spinnerClientes = (Spinner) findViewById(R.id.spinnerClientes);

        ////////////////////////////////LLENADO SPINNER/////////////////////////////////////////////
        consultarListaClientes();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaClientes);
        spinnerClientes.setAdapter(adaptador);
        ////////////////////////////////////////////////////////////////////////////////////////////

        //CALENDARIO
        mCurrentDate  = Calendar.getInstance();
        dia = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        mes = mCurrentDate.get(Calendar.MONTH);
        ano = mCurrentDate.get(Calendar.YEAR);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //this line shows back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nueva Venta");
        getSupportActionBar().setElevation(0);

        etCodVenta = (EditText) findViewById(R.id.etCodVenta);
        etTotal = (EditText) findViewById(R.id.etTotal);

        btFecha = (Button) findViewById(R.id.btFecha);
        btFecha.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DatePickerDialog datePickerDialog = new DatePickerDialog(NuevaVenta.this,
                        new DatePickerDialog.OnDateSetListener()
                        {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear,
                                                  int dayOfMonth)
                            {
                                monthOfYear = monthOfYear + 1;
                                tvValorFecha.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                                Fecha = dayOfMonth + "/" + monthOfYear + "/" + year;
                            }
                        }, ano, mes, dia);
                datePickerDialog.show();
            }
        });


        tvValorFecha = (TextView) findViewById(R.id.tvValorFecha);

        rbMinutos = (RadioButton) findViewById(R.id.rbMinutos);
        rbAlquiler = (RadioButton) findViewById(R.id.rbAlquiler);

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        btRegistrarVenta = (Button) findViewById(R.id.btRegistrarVenta);
        btRegistrarVenta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Cliente = spinnerClientes.getSelectedItem().toString();
                Fecha = tvValorFecha.getText().toString();
                CodVenta = etCodVenta.getText().toString();
                Total = etTotal.getText().toString();

                if (TextUtils.isEmpty(CodVenta))
                {
                    etCodVenta.setError("Digite el Cod. de la venta");
                    view.requestFocus();
                    return;
                }
                if (rbMinutos.isChecked())
                {
                    TipoServicio = "Minutos";
                }
                if (rbAlquiler.isChecked())
                {
                    TipoServicio = "Alquiler Modem";
                }
                if (TextUtils.isEmpty(TipoServicio))
                {
                    Toast.makeText(NuevaVenta.this,"¡Seleccione el tipo de Servicio!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Fecha))
                {
                    Toast.makeText(NuevaVenta.this,"¡Seleccione la fecha!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Total))
                {
                    Toast.makeText(NuevaVenta.this,"¡Seleccione el valor total!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //TENGO QUE LLAMAR LA BD ESCRIBIBLE
                SQLiteDatabase db = ayudabd.getWritableDatabase();//YA TENGO LA BD LISTA PARA ESCRIBIR
                ContentValues valores = new ContentValues();
                valores.put(helper.COLUMNA_CODVENTA,CodVenta);//LLAVES
                valores.put(helper.COLUMNA_CODCLIENTE, Cliente);//VALORES QUE VOY A METER EN LA TABLA
                valores.put(helper.COLUMNA_TIPOSERVICIO, TipoServicio);
                valores.put(helper.COLUMNA_FECHA, Fecha);
                valores.put(helper.COLUMNA_PRECIO, Total);

                Long IdGuardado = db.insert(helper.TABLA_VENTAS, helper.COLUMNA_CODVENTA, valores);//RETORNA
                if (IdGuardado == -1) {
                    Toast.makeText(getApplicationContext(), "No se pudo guardar, la venta ya existe!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Se guardó la venta: "+IdGuardado, Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private void consultarListaClientes() {
        final AyudaBD ayudabd = new AyudaBD(getApplicationContext());
        SQLiteDatabase db = ayudabd.getWritableDatabase();
        Clientes cliente = null;
        clientesList = new ArrayList<Clientes>();
        Cursor c = db.rawQuery("SELECT * FROM "+ helper.TABLA_CLIENTES,null);

        while (c.moveToNext()){
            cliente = new Clientes();
            cliente.setNoId(c.getInt(0));
            cliente.setNombre(c.getString(1));

            clientesList.add(cliente);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaClientes = new ArrayList<String>();

        for(int i=0;i<clientesList.size();i++){
            listaClientes.add(clientesList.get(i).getNoId()+" - "+clientesList.get(i).getNombre());
        }
    }


}
