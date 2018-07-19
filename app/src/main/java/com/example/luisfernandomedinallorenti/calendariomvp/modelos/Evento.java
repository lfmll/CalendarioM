package com.example.luisfernandomedinallorenti.calendariomvp.modelos;

import android.content.ContentValues;

import com.example.luisfernandomedinallorenti.calendariomvp.datos.SQLConstantes;

public class Evento {
    private int id;
    private String nombre;
    private String descripcion;
    private String fecha;

    public Evento(){}

    public Evento(String nombre, String descripcion, String fecha) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    //Contenedor
    public ContentValues toValues(){
        ContentValues contentValues=new ContentValues(3);
        contentValues.put(SQLConstantes.COLUMN_NOMBRE,nombre);
        contentValues.put(SQLConstantes.COLUMN_DESCRIPCION,descripcion);
        contentValues.put(SQLConstantes.COLUMN_FECHA,fecha);
        return contentValues;
    }
}
