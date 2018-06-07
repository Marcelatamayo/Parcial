package com.example.marcela.parcial2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity
{

    EditText etCorreo, etContraseña;
    Button btIniciarSesion;
    TextView tvCuentaOlvidada;
    String correo, contraseña;
    private ProgressDialog pDialog;
    Pattern pattern = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etContraseña = (EditText) findViewById(R.id.etContraseña);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        btIniciarSesion = (Button) findViewById(R.id.btIniciarSesion);
        tvCuentaOlvidada = (TextView) findViewById(R.id.tvCuentaOlvidada);

        btIniciarSesion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                correo = etCorreo.getText().toString();
                contraseña = etContraseña.getText().toString();

                if (TextUtils.isEmpty(correo))
                {
                    etCorreo.setError("Digite su Correo");
                    view.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(contraseña))
                {
                    etContraseña.setError("Digite su Contraseña");
                    view.requestFocus();
                    return;
                }

                Matcher mather = pattern.matcher(correo);
                if(!mather.find()) {
                    etCorreo.setError("El email ingresado es inválido.");
                    return;
                }

                if(correo.equals("ricardojorge@gmail.com") && contraseña.equals("123456"))
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }else
                    {
                        Toast.makeText(getApplicationContext(),"¡Usuario Inválido!", Toast.LENGTH_LONG).show();
                    }

            }
        });

        tvCuentaOlvidada.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "Contáctese con los desarrolladores " +
                        "de la app.", Toast.LENGTH_LONG).show();

            }
        });
    }
}
