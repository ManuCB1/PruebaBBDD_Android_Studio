package com.example.pruebabbdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnInsertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertar = findViewById(R.id.btnInsertar);
        btnInsertar.setOnClickListener(v -> {
            Intent cambiarInicioInsertar = new Intent(v.getContext(), MainInsertar.class);
            startActivity(cambiarInicioInsertar);
        });

    }
}