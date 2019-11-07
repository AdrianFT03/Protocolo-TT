package com.example.in_help.ui;

import java.io.Serializable;

public class UpdatePlacas implements Serializable {
    public UpdatePlacas() {
        this.nu_placas = nu_placas;
        this.id_vehiculo = id_vehiculo;
    }

    public String getNu_placas() {
        return nu_placas;
    }

    public void setNu_placas(String nu_placas) {
        this.nu_placas = nu_placas;
    }

    public Integer getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(Integer id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    @Override
    public String toString() {
        return "UpdatePlacas{" +
                "nu_placas='" + nu_placas +
                ", id_vehiculo=" + id_vehiculo +
                '}';
    }

    String nu_placas;
    Integer id_vehiculo;
}
