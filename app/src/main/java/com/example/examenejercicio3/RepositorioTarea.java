package com.example.examenejercicio3;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//Clase RepositorioTarea para gestionar las tareas que sigue un patrón Singleton
public class RepositorioTarea {

    //Variables
    private static RepositorioTarea instancia;
    private List<Tarea> tareasPendientes;
    private List<Tarea> tareasHechas;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private static final String PREFS_NAME = "tareas_prefs";
    private static final String KEY_TAREAS_PENDIENTES = "tareas_pendientes";
    private static final String KEY_TAREAS_HECHAS = "tareas_hechas";

    //Constructor privado
    private RepositorioTarea(Context context) {
        tareasPendientes = new ArrayList<>();
        tareasHechas = new ArrayList<>();
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
        cargarTareas();
    }

    //Método para obtener la instancia de RepositorioTarea
    public static RepositorioTarea getInstancia(Context context) {
        if (instancia == null) {
            instancia = new RepositorioTarea(context);
        }
        return instancia;
    }

    //Getters & Setters
    public List<Tarea> getTareasPendientes() {
        return tareasPendientes;
    }
    public void setTareasPendientes(List<Tarea> tareasPendientes) {
        this.tareasPendientes = tareasPendientes;
    }
    public List<Tarea> getTareasHechas() {
        return tareasHechas;
    }
    public void setTareasHechas(List<Tarea> tareasHechas) {
        this.tareasHechas = tareasHechas;
    }

    //Método para agregar una tarea a la lista principal de tareas pendientes
    public void agregarTareaPendiente(Tarea tarea) {
        tareasPendientes.add(tarea);
        guardarTareas();
    }

    //Método para marcar una tarea como hecha y moverla a la lista de tareas hechas
    public void marcarTareaHecha(int position) {
        Tarea tarea = tareasPendientes.remove(position);
        tarea.setHecha(true);
        tareasHechas.add(tarea);
        guardarTareas();
    }

    //Método para desmarcar una tarea hecha y moverla a la lista de tareas pendientes
    public void desmarcarTareaHecha(int position) {
        Tarea tarea = tareasHechas.remove(position);
        tarea.setHecha(false);
        tareasPendientes.add(tarea);
        guardarTareas();
    }

    //Método para eliminar una tarea hecha de la lista de tareas hechas
    public void eliminarTareaHecha(int position) {
        tareasHechas.remove(position);
        guardarTareas();
    }

    //Método para eliminar una tarea pendiente de la lista principal de tareas pendientes
    public void eliminarTareaPendiente(int position) {
        tareasPendientes.remove(position);
        guardarTareas();
    }

    //Método para guardar las listas de tareas en SharedPreferences
    private void guardarTareas() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TAREAS_PENDIENTES, gson.toJson(tareasPendientes));
        editor.putString(KEY_TAREAS_HECHAS, gson.toJson(tareasHechas));
        editor.apply();
    }

    //Método para cargar las listas de tareas desde SharedPreferences
    private void cargarTareas() {
        String jsonPendientes = sharedPreferences.getString(KEY_TAREAS_PENDIENTES, null);
        String jsonHechas = sharedPreferences.getString(KEY_TAREAS_HECHAS, null);
        Type type = new TypeToken<List<Tarea>>() {}.getType();
        if (jsonPendientes != null) {
            tareasPendientes = gson.fromJson(jsonPendientes, type);
        }
        if (jsonHechas != null) {
            tareasHechas = gson.fromJson(jsonHechas, type);
        }
    }
}