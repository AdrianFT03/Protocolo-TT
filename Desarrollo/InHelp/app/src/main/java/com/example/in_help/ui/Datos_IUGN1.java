package com.example.in_help.ui;

import java.io.Serializable;

public class Datos_IUGN1 implements Serializable {

    Integer Id;
    Integer Imagen1;
    String Placas;
    String Fh_notificacion;
    Integer Imagen2;
    Float Longitud;
    Float Latitud;

    public Datos_IUGN1(Integer id, Integer imagen1, String placas, String fh_notificacion, Integer imagen2, Float longitud, Float latitud) {
        Id = id;
        Imagen1 = imagen1;
        Placas = placas;
        Fh_notificacion = fh_notificacion;
        Imagen2 = imagen2;
        Longitud = longitud;
        Latitud = latitud;
    }

    public Float getLongitud() {
        return Longitud;
    }

    public void setLongitud(Float longitud) {
        Longitud = longitud;
    }

    public Float getLatitud() {
        return Latitud;
    }

    public void setLatitud(Float latitud) {
        Latitud = latitud;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getImagen1() {
        return Imagen1;
    }

    public void setImagen1(Integer imagen1) {
        Imagen1 = imagen1;
    }

    public String getPlacas() {
        return Placas;
    }

    public void setPlacas(String placas) {
        Placas = placas;
    }

    public String getFh_notificacion() {
        return Fh_notificacion;
    }

    public void setFh_notificacion(String fh_notificacion) {
        Fh_notificacion = fh_notificacion;
    }

    public Integer getImagen2() {
        return Imagen2;
    }

    public void setImagen2(Integer imagen2) {
        Imagen2 = imagen2;
    }
}
