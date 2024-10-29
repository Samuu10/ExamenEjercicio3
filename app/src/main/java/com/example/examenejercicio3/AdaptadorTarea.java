package com.example.examenejercicio3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

//Clase AdaptadorTarea para gestionar la lista de tareas en un RecyclerView
public class AdaptadorTarea extends RecyclerView.Adapter<AdaptadorTarea.TareaViewHolder> {

    //Variables
    private List<Tarea> listaTareas;
    private OnTareaClickListener listener;

    //Constructor
    public AdaptadorTarea(List<Tarea> listaTareas, OnTareaClickListener listener) {
        this.listaTareas = listaTareas;
        this.listener = listener;
    }

    //Interfaz para gestionar los eventos de clic en las tareas
    public interface OnTareaClickListener {
        void onTareaHecha(int position, boolean isChecked);
        void onTareaEliminada(int position);
        void onItemClick(Tarea tarea);
    }

    //Método onCreateViewHolder para inflar el layout de cada item de la lista
    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(view);
    }

    //Método onBindViewHolder para asignar los datos de cada tarea a los elementos de la vista
    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        Tarea tarea = listaTareas.get(position);
        holder.tvNombreTarea.setText(tarea.getNombre());
        holder.cbHecha.setChecked(tarea.isHecha());
        holder.cbHecha.setOnCheckedChangeListener((buttonView, isChecked) -> listener.onTareaHecha(holder.getAdapterPosition(), isChecked));
        holder.ivEliminar.setOnClickListener(v -> listener.onTareaEliminada(holder.getAdapterPosition()));
        holder.itemView.setOnClickListener(v -> listener.onItemClick(tarea));
    }

    //Método getItemCount para obtener la cantidad de tareas en la lista
    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    //Clase estática TareaViewHolder para representar los elementos de la vista
    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreTarea;
        CheckBox cbHecha;
        ImageView ivEliminar;

        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreTarea = itemView.findViewById(R.id.tvNombreTarea);
            cbHecha = itemView.findViewById(R.id.cbHecha);
            ivEliminar = itemView.findViewById(R.id.ivEliminar);
        }
    }
}