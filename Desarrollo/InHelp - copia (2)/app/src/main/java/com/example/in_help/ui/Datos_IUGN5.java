package com.example.in_help.ui;

import java.io.Serializable;

public class Datos_IUGN5 implements Serializable {


    public Datos_IUGN5(int id, String placas, int imagen1, int imagen2) {
        Id = id;
        Placas = placas;
        Imagen1 = imagen1;
        Imagen2 = imagen2;
    }

    private int Id;
    private String Placas;
    private int Imagen1;
    private int Imagen2;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPlacas() {
        return Placas;
    }

    public void setPlacas(String placas) {
        Placas = placas;
    }

    public int getImagen1() {
        return Imagen1;
    }

    public void setImagen1(int imagen1) {
        Imagen1 = imagen1;
    }

    public int getImagen2() {
        return Imagen2;
    }

    public void setImagen2(int imagen2) {
        Imagen2 = imagen2;
    }

}
