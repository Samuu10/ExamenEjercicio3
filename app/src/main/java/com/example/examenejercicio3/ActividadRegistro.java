package com.example.examenejercicio3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class ActividadRegistro extends AppCompatActivity {

    //Variables
    private EditText etNombre, etDescripcion, etFecha, etPrecio;
    private Spinner spPrioridad;
    private Button btnAñadir, btnCancelar;
    private RepositorioTarea repositorioTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_registro);

        repositorioTarea = RepositorioTarea.getInstancia(this);

        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        etFecha = findViewById(R.id.etFecha);
        etPrecio = findViewById(R.id.etPrecio);
        spPrioridad = findViewById(R.id.spPrioridad);

        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnAñadir = findViewById(R.id.btnAñadir);
        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarTarea();
            }
        });

        btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActividadRegistro.this, ActividadTareasPendientes.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                etFecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void guardarTarea() {
        String nombre = etNombre.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String fecha = etFecha.getText().toString();
        String prioridad = spPrioridad.getSelectedItem().toString();
        String precioStr = etPrecio.getText().toString();

        if (nombre.isEmpty() || descripcion.isEmpty() || fecha.isEmpty() || precioStr.isEmpty()) {
            Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double precio = Double.parseDouble(precioStr);

        Tarea tarea = new Tarea(nombre, descripcion, fecha, prioridad, precio, false);
        repositorioTarea.agregarTareaPendiente(tarea);

        Intent intent = new Intent(ActividadRegistro.this, ActividadTareasPendientes.class);
        startActivity(intent);
        finish();
    }
}