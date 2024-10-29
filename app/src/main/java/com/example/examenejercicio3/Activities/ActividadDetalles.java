package com.example.examenejercicio3.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.examenejercicio3.R;
import com.example.examenejercicio3.Tarea;

//Clase que representa la actividad de los detalles de una tarea
public class ActividadDetalles extends AppCompatActivity {

    //Variables
    private TextView nombre, descripcion, fecha, prioridad, precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_detalles);

        //Se asignan los TextView a las variables
        nombre = findViewById(R.id.nombre);
        descripcion = findViewById(R.id.descripcion);
        fecha = findViewById(R.id.fecha);
        prioridad = findViewById(R.id.prioridad);
        precio = findViewById(R.id.precio);

        Tarea tarea = (Tarea) getIntent().getSerializableExtra("tarea");

        //Si la tarea no es nula, se asignan los valores a los TextView
        if (tarea != null) {
            nombre.setText(tarea.getNombre());
            descripcion.setText(tarea.getDescripcion());
            fecha.setText(tarea.getFecha());
            prioridad.setText(tarea.getPrioridad());
            precio.setText(String.valueOf(tarea.getPrecio()));

            //Se asigna un color a la prioridad de la tarea
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

        //Botón para volver a la actividad anterior
        Button btnVolver = findViewById(R.id.boton_volver);
        btnVolver.setOnClickListener(v -> finish());
    }
}