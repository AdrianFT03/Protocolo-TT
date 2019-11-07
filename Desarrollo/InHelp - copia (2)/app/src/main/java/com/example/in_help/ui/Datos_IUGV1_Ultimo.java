package com.example.in_help.ui;

class Datos_IUGV1_Ultimo {
    Integer id_vehiculo;

    @Override
    public String toString() {
        return "Datos_IUGV1_Ultimo{" +
                "id_vehiculo=" + id_vehiculo +
                '}';
    }

    public Datos_IUGV1_Ultimo() {
        this.id_vehiculo = id_vehiculo;
    }

    public Integer getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(Integer id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }
}
