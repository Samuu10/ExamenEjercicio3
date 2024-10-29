package com.example.examenejercicio3;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ActividadTareasPendientes extends AppCompatActivity implements  AdaptadorTarea.OnTareaClickListener {

    //Variables
    private AdaptadorTarea adapterTarea;
    private RepositorioTarea repositorioTarea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_tareas_pendientes);

        repositorioTarea = RepositorioTarea.getInstancia(this);
        List<Tarea> tareasPendientes = repositorioTarea.getTareasPendientes();

        adapterTarea = new AdaptadorTarea(tareasPendientes, this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_tareas_pendientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterTarea);

        Button btnAnadirTarea = findViewById(R.id.boton_anadir_tarea);
        btnAnadirTarea.setOnClickListener(v -> {
            Intent intent = new Intent(ActividadTareasPendientes.this, ActividadRegistro.class);
            startActivityForResult(intent, 1);
        });

        Button btnVerTareasHechas = findViewById(R.id.boton_tareas_hechas);
        btnVerTareasHechas.setOnClickListener(v -> {
            Intent intent = new Intent(ActividadTareasPendientes.this, ActividadTareasHechas.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterTarea.notifyDataSetChanged();
    }

    //Método para marcar una tarea como hecha
    @Override
    public void onTareaHecha(int position, boolean isChecked) {
        if (isChecked) {
            new AlertDialog.Builder(this)
                .setTitle("Confirmar")
                .setMessage("¿Estás seguro de que quieres marcar esta tarea como completada?")
                .setPositiveButton("Confirmar", (dialog, which) -> {
                    repositorioTarea.marcarTareaHecha(position);
                    adapterTarea.notifyDataSetChanged();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    adapterTarea.notifyItemChanged(position);
                })
                .show();
        }
    }

    //Método para eliminar una tarea de la lista principal de tareas pendientes
    @Override
    public void onTareaEliminada(int position) {
        new AlertDialog.Builder(this)
            .setTitle("Confirmar")
            .setMessage("¿Estás seguro de que quieres eliminar esta tarea?")
            .setPositiveButton("Confirmar", (dialog, which) -> {
                repositorioTarea.eliminarTareaPendiente(position);
                adapterTarea.notifyDataSetChanged();
            })
            .setNegativeButton("Cancelar", null)
            .show();
    }

    //Método para ir a ActividadDetalles para ver los detalles de una tarea
    @Override
    public void onItemClick(Tarea tarea) {
        Intent intent = new Intent(ActividadTareasPendientes.this, ActividadDetalles.class);
        intent.putExtra("tarea", tarea);
        startActivity(intent);
    }

    //Método para manejar el resultado de la actividad de registro de tareas
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Tarea nuevaTarea = (Tarea) data.getSerializableExtra("nueva_tarea");
            if (nuevaTarea != null) {
                repositorioTarea.agregarTareaPendiente(nuevaTarea);
                adapterTarea.notifyDataSetChanged();
            }
        }
    }
}