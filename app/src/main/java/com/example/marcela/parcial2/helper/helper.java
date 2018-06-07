package com.example.marcela.parcial2.helper;

/**
 * Created by Marcela on 15/05/2018.
 */

public class helper {

    ///////////////////////////TABLA CLIENTES//////////////////////////////////////////////////
    public static final String TABLA_CLIENTES = "CLIENTES";
    public static final String COLUMNA_ID = "id";
    public static final String COLUMNA_NOMBRES = "nombres";
    public static final String COLUMNA_APELLIDOS = "apellidos";
    public static final String COLUMNA_DIRECCION = "direccion";
    public static final String COLUMNA_TELEFONOS = "telefono";

    public static final String CREAR_TABLA_CLIENTES="CREATE TABLE " +
            ""+TABLA_CLIENTES+" ("+COLUMNA_ID+" " +
            "INTEGER PRIMARY KEY, "+COLUMNA_NOMBRES+" TEXT,"+COLUMNA_APELLIDOS+" TEXT," +
            COLUMNA_DIRECCION+ " TEXT," + COLUMNA_TELEFONOS + " INTEGER)";

    ///////////////////////////TABLA VENTAS/////////////////////////////////////////////////////
    public static final String TABLA_VENTAS = "VENTAS";
    public static final String COLUMNA_CODVENTA = "codVenta";
    public static final String COLUMNA_CODCLIENTE = "codCliente";
    public static final String COLUMNA_TIPOSERVICIO = "tipoServicio";
    public static final String COLUMNA_FECHA = "fecha";
    public static final String COLUMNA_PRECIO = "precio";

    public static final String CREAR_TABLA_VENTAS="CREATE TABLE " +
            ""+TABLA_VENTAS+" ("+COLUMNA_CODVENTA+" " +
            "INTEGER PRIMARY KEY, "+COLUMNA_CODCLIENTE+" INTEGER,"+COLUMNA_TIPOSERVICIO+" TEXT," +
            COLUMNA_FECHA+ " TEXT," + COLUMNA_PRECIO + " INTEGER)";

}
