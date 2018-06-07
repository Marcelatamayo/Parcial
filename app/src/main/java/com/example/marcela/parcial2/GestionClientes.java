package com.example.marcela.parcial2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcela.parcial2.helper.helper;

public class GestionClientes extends AppCompatActivity {

    Button btRegistrar, btEliminar, btActualizar, btBuscar;
    EditText etNoId, etNombre, etApellidos, etDireccion, etTelefono;
    String NoId, Nombre, Apellidos, Direccion, Telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_clientes);

        btRegistrar = (Button) findViewById(R.id.btRegistrar);
        btEliminar = (Button) findViewById(R.id.btEliminar);
        btActualizar = (Button) findViewById(R.id.btActualizar);
        btBuscar = (Button) findViewById(R.id.btBuscar);

        etNoId = (EditText) findViewById(R.id.etNoId);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etDireccion = (EditText) findViewById(R.id.etDireccion);
        etTelefono = (EditText) findViewById(R.id.etTelefono);

        //SE CREA EL OBJETO DE BD
        final AyudaBD ayudabd = new AyudaBD(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //this line shows back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Gestión Clientes");
        getSupportActionBar().setElevation(0);

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

       ////////////////////////EVENTOS BOTONES/////////////////////////////////////////////////////

        btRegistrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                NoId = etNoId.getText().toString();
                Nombre = etNombre.getText().toString();
                Apellidos = etApellidos.getText().toString();
                Direccion = etDireccion.getText().toString();
                Telefono = etTelefono.getText().toString();

                if (TextUtils.isEmpty(NoId))
                {
                    etNoId.setError("Digite su No. de Id.");
                    view.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Nombre))
                {
                    etNombre.setError("Digite su Nombre");
                    view.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Apellidos))
                {
                    etApellidos.setError("Digite su apellido");
                    view.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Direccion))
                {
                    etDireccion.setError("Digite su dirección");
                    view.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Telefono))
                {
                    etTelefono.setError("Digite su teléfono");
                    view.requestFocus();
                    return;
                }

                //TENGO QUE LLAMAR LA BD ESCRIBIBLE
                SQLiteDatabase db = ayudabd.getWritableDatabase();//YA TENGO LA BD LISTA PARA ESCRIBIR
                ContentValues valores = new ContentValues();
                valores.put(helper.COLUMNA_ID, NoId);//LLAVES
                valores.put(helper.COLUMNA_NOMBRES, Nombre);//VALORES QUE VOY A METER EN LA TABLA
                valores.put(helper.COLUMNA_APELLIDOS, Apellidos);
                valores.put(helper.COLUMNA_DIRECCION, Direccion);
                valores.put(helper.COLUMNA_TELEFONOS, Telefono);

                Long IdGuardado = db.insert(helper.TABLA_CLIENTES, helper.COLUMNA_ID, valores);//RETORNA
                if (IdGuardado == -1){
                    Toast.makeText(getApplicationContext(), "No se pudo guardar, el cliente ya existe!", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Se guardó el Cliente: "+IdGuardado, Toast.LENGTH_LONG).show();
                }
            }

        });

        btEliminar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                NoId = etNoId.getText().toString();
                    if (TextUtils.isEmpty(NoId)) {
                        etNoId.setError("Digite el No. de Id.");
                        view.requestFocus();
                        return;
                    }

                SQLiteDatabase db = ayudabd.getWritableDatabase();
                String[] argsel = {NoId};
                db.delete(helper.TABLA_CLIENTES,helper.COLUMNA_ID+"=?",argsel);
                Toast.makeText(getApplicationContext(),"Cliente Eliminado",Toast.LENGTH_LONG).show();
                etNoId.setText("");
                etNombre.setText("");
                etApellidos.setText("");
                etDireccion.setText("");
                etTelefono.setText("");

            }
        });

        btActualizar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                NoId = etNoId.getText().toString();
                Nombre = etNombre.getText().toString();
                Apellidos = etApellidos.getText().toString();
                Direccion = etDireccion.getText().toString();
                Telefono = etTelefono.getText().toString();

                if (TextUtils.isEmpty(NoId))
                {
                    etNoId.setError("Digite su No. de Id.");
                    view.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Nombre))
                {
                    etNombre.setError("Digite su Nombre");
                    view.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Apellidos))
                {
                    etApellidos.setError("Digite su apellido");
                    view.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Direccion))
                {
                    etDireccion.setError("Digite su dirección");
                    view.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Telefono))
                {
                    etTelefono.setError("Digite su teléfono");
                    view.requestFocus();
                    return;
                }

                SQLiteDatabase db = ayudabd.getWritableDatabase();
                String[] argsel = {NoId};
                ContentValues valores = new ContentValues();
                valores.put(helper.COLUMNA_NOMBRES, Nombre);
                valores.put(helper.COLUMNA_APELLIDOS, Apellidos);
                valores.put(helper.COLUMNA_DIRECCION, Direccion);
                valores.put(helper.COLUMNA_TELEFONOS, Telefono);

                db.update(helper.TABLA_CLIENTES,valores,helper.COLUMNA_ID+"=?",argsel);
                Toast.makeText(getApplicationContext(),"Se actualizó el cliente",Toast.LENGTH_LONG).show();

            }
        });

        btBuscar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SQLiteDatabase db = ayudabd.getReadableDatabase();//DECLARAR LA BD COMO LECTURA
                String[] argsel = {etNoId.getText().toString()};//ARGUMENTO DE SELECCION

                try
                {
                    Cursor c = db.rawQuery("SELECT "+helper.COLUMNA_NOMBRES+","+helper.COLUMNA_APELLIDOS+
                            "," + helper.COLUMNA_DIRECCION + "," + helper.COLUMNA_TELEFONOS +
                            " FROM "+helper.TABLA_CLIENTES+" WHERE "+helper.COLUMNA_ID+"=? ",argsel);//VA CONSULTAR EN NOMRRE_TABLA
                    // LOS DATOS COMPARANDO LA COLUMNA
                    // ID CON LO QUE ESTA EN LA CAJA DE TEXTO (ARGSEL)
                    //COMO LOS VOY A PONER?
                    c.moveToFirst();//
                    etNombre.setText(c.getString(0));//PROYECCION COLUMNA NOMBRES
                    etApellidos.setText(c.getString(1));
                    etDireccion.setText(c.getString(2));
                    etTelefono.setText(c.getString(3));

                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "No se encontró el Cliente", Toast.LENGTH_LONG).show();
                    etNoId.setText("");
                    etNombre.setText("");
                    etApellidos.setText("");
                    etDireccion.setText("");
                    etTelefono.setText("");

                }
            }
        });
    }
}
