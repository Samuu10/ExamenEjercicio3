package com.example.examenejercicio3;

import java.io.Serializable;

//Clase Tarea que representa una tarea con sus atributos, implementa Serializable para poder ser guardada en SharedPreferences
public class Tarea implements Serializable {

    //Atributos
    private String nombre;
    private String descripcion;
    private String fecha;
    private String prioridad;
    private double precio;
    private boolean hecha;

    // Constructor, getters y setters
    public Tarea(String nombre, String descripcion, String fecha, String prioridad, double precio, boolean hecha) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.prioridad = prioridad;
        this.precio = precio;
        this.hecha = hecha;
    }

    //Getters & Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getPrioridad() {
        return prioridad;
    }
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public boolean isHecha() {
        return hecha;
    }
    public void setHecha(boolean hecha) {
        this.hecha = hecha;
    }
}