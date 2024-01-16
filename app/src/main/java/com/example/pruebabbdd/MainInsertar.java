package com.example.pruebabbdd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainInsertar extends AppCompatActivity {

    private TextView textNombre, textEdad, textEmail;
    private Button btnAnadir, btnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_insertar);

        textNombre = findViewById(R.id.textNombre);
        textEdad = findViewById(R.id.textEdad);
        textEmail = findViewById(R.id.textEmail);
        btnAnadir = findViewById(R.id.btnAnadir);
        btnConsultar = findViewById(R.id.btnConsultar);
        BDAdaptador bdAdaptador = new BDAdaptador(this);

        btnAnadir.setOnClickListener(view -> {
            long resultado = bdAdaptador.insertar(textNombre.getText().toString(), textEdad.getText().toString(), textEmail.getText().toString());
            if (resultado != -1) Toast.makeText(MainInsertar.this, "Añadido Correctamente", Toast.LENGTH_SHORT).show();
            else Toast.makeText(MainInsertar.this, "Error al añadir", Toast.LENGTH_SHORT).show();
        });

        btnConsultar.setOnClickListener(view ->{
                    String nombre = bdAdaptador.consultarId(new String[]{"16"});
                    Toast.makeText(this, "Nombre: "+nombre, Toast.LENGTH_SHORT).show();
        });

    }
}