package com.example.pruebabbdd;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

//Crear una clase que extienda de SQLiteOpenHelper
public class BaseDatos extends SQLiteOpenHelper {
    Context contexto;
    static BaseDatos baseDatos;

    //Constructor
    public BaseDatos(Context context) {
        //Pasamos al constructor de la superclase el contexto, el nombre de la BD y la Versión
        super(context, "PruebaBD", null, 1);
        //Almacenamos el contexto
        contexto = context;
    }

    public static BaseDatos getInstance(Context c) {
        if (baseDatos == null){
            return baseDatos = new BaseDatos(c);
        }
        return baseDatos;
    }

    @Override
    //onCreate se ejecuta cuando se CREA la BD
    public void onCreate(SQLiteDatabase db) {
        try {
            //Creamos la tabla
            db.execSQL("CREATE TABLE alumnos " +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre VARCHAR, edad VARCHAR, email VARCHAR)");
        } catch (SQLException e) {
            //Mensaje de error si no se ha ejecutado correctamente
            Toast.makeText(contexto, "" + e, Toast.LENGTH_SHORT).show();
        }
    }

    //onUpgrade se ejecuta cuando se ACTUALIZA la versíon de la BD
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            //Eliminamos la tabla anterior (si existe)
            db.execSQL("DROP TABLE IF EXISTS alumnos");
            //Llamamos a onCreate para que cree la tabla con las nuevas especificaciones
            onCreate(db);
        } catch (SQLException e) {
            //Mensaje de error si no se ha ejecutado correctamente
            Toast.makeText(contexto, "" + e, Toast.LENGTH_SHORT).show();
        }

    }
}
