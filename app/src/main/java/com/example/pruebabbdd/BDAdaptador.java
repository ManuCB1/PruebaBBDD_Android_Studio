package com.example.pruebabbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class BDAdaptador {
    private Context contexto;
    private BaseDatos baseDatos;
    private SQLiteDatabase bd;
    public BDAdaptador(Context c){
        //Almacenamos el contexto
        contexto=c;
        //Creamos una instancia a la Base de Datos
        baseDatos=BaseDatos.getInstance(c);
    }
    //Método para insertar datos en la BD. Recibe los parámetros a insertar
    public long insertar(String nombre, String edad, String email){
        //Abrimos la BD en modo lectura/escritura
        bd=baseDatos.getWritableDatabase();
        //Preparamos la información a insertar
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre",nombre);
        contentValues.put("edad",edad);
        contentValues.put("email",email);
        //Insertarmos los datos. Recogemos el resultado
        long resultado=bd.insert("alumnos",null,contentValues);
        //Resultado devuelve el Id añadido
        //Cerramos la BD
        bd.close();
        //Devolvemos el resultado de la inserción
        return resultado;
    }


    //Consulta
    public void consultar(){
        //Abrimos la BD en modo lectura
        bd=baseDatos.getReadableDatabase();
        String[] columnas = {"_id", "nombre", "edad", "email"};
        //Cursor realiza consulta
        Cursor cursor = bd.query("alumnos", columnas, null, null, null, null, null);
        //Resultados
        if (cursor != null && cursor.moveToFirst()){
            while (cursor.moveToNext()){
                int idIndex = cursor.getColumnIndex("_id");
                int nombreIndex = cursor.getColumnIndex("nombre");
                int edadIndex = cursor.getColumnIndex("edad");
                int emailIndex = cursor.getColumnIndex("email");

                //Coger valores
                int id = cursor.getInt(idIndex);
                String nombre = cursor.getString(nombreIndex);
                String edad = cursor.getString(edadIndex);
                String email = cursor.getString(emailIndex);

                //Log Consulta
                Log.i("Consulta", "Id="+id+"\nNombre="+nombre+ "\nEdad="+edad+"\nEmail="+email);
            };
            cursor.close();
        }
        bd.close();
    }

    public String consultarId(String[] parametros){
        //Abrimos la BD en modo lectura
        bd=baseDatos.getReadableDatabase();
        //Consulta
        String query = "SELECT nombre FROM alumnos where _id = ?";
        Cursor cursor = bd.rawQuery(query, parametros);
        String nombre = "";
        if (cursor != null && cursor.moveToFirst()){
            nombre = cursor.getString(0);
        }

        return nombre;
    }

}
