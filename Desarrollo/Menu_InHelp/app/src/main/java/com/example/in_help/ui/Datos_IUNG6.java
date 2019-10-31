package com.example.in_help.ui;

import java.io.Serializable;

public class Datos_IUNG6 implements Serializable{


    private int id;
    private boolean id_switch;
    private int id_imagen1;
    private String nombre;
    private int id_imagen2;

    public Datos_IUNG6(int id, boolean id_switch, int id_imagen1, String nombre, int id_imagen2) {
        this.id = id;
        this.id_switch = id_switch;
        this.id_imagen1 = id_imagen1;
        this.nombre = nombre;
        this.id_imagen2 = id_imagen2;
    }

    public boolean getId_switch() {
        return id_switch;
    }

    public void setId_switch(boolean id_switch) {
        this.id_switch = id_switch;
    }

    public int getId_imagen1() {
        return id_imagen1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_imagen1(int id_imagen1) {
        this.id_imagen1 = id_imagen1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_imagen2() {
        return id_imagen2;
    }

    public void setId_imagen2(int id_imagen2) {
        this.id_imagen2 = id_imagen2;
    }
}
