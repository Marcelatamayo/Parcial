package com.example.marcela.parcial2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.marcela.parcial2.helper.helper;

import java.util.ArrayList;

/**
 * Created by Camilo on 14/05/2018.
 */

public class AyudaBD extends SQLiteOpenHelper
{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CabinasRJ.db";

    public AyudaBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //SE CREA LA TABLA
        db.execSQL(helper.CREAR_TABLA_CLIENTES);
        db.execSQL(helper.CREAR_TABLA_VENTAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //SE ELIMINA LA TABLA
        db.execSQL( "DROP TABLE IF EXISTS " + helper.TABLA_CLIENTES);
        db.execSQL( "DROP TABLE IF EXISTS " + helper.TABLA_VENTAS);
        //VUELVO A LLAMAR EL ONCREATE
        onCreate(db);

    }

}
