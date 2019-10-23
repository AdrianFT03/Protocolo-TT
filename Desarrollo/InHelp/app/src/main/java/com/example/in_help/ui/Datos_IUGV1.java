package com.example.in_help.ui;

import java.io.Serializable;

public class Datos_IUGV1 implements Serializable {

    public Datos_IUGV1(int id, String placas, int imagen1, int imagen2, int imagen3) {
        Id = id;
        Placas = placas;
        Imagen1 = imagen1;
        Imagen2 = imagen2;
        Imagen3 = imagen3;
    }

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

    public int getImagen3() {
        return Imagen3;
    }

    public void setImagen3(int imagen3) {
        Imagen3 = imagen3;
    }

    @Override
    public String toString() {
        return "Datos_IUGV1{" +
                "Id=" + Id +
                ", Placas='" + Placas +
                ", Imagen1=" + Imagen1 +
                ", Imagen2=" + Imagen2 +
                ", Imagen3=" + Imagen3 +
                '}';
    }

    private int Id;
    private String Placas;
    private int Imagen1;
    private int Imagen2;
    private int Imagen3;
}
