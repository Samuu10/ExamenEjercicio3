package com.example.examenejercicio3;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ActividadDetalles extends AppCompatActivity {

    private TextView nombre, descripcion, fecha, prioridad, precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_detalles);

        nombre = findViewById(R.id.nombre);
        descripcion = findViewById(R.id.descripcion);
        fecha = findViewById(R.id.fecha);
        prioridad = findViewById(R.id.prioridad);
        precio = findViewById(R.id.precio);

        Tarea tarea = (Tarea) getIntent().getSerializableExtra("tarea");

        if (tarea != null) {
            nombre.setText(tarea.getNombre());
            descripcion.setText(tarea.getDescripcion());
            fecha.setText(tarea.getFecha());
            prioridad.setText(tarea.getPrioridad());
            precio.setText(String.valueOf(tarea.getPrecio()));

            switch (tarea.getPrioridad()) {
                case "BAJA":
                    prioridad.setTextColor(Color.GREEN);
                    break;
                case "NORMAL":
                    prioridad.setTextColor(Color.YELLOW);
                    break;
                case "ALTA":
                    prioridad.setTextColor(Color.RED);
                    break;
            }
        }

        Button btnVolver = findViewById(R.id.boton_volver);
        btnVolver.setOnClickListener(v -> finish());
    }
}