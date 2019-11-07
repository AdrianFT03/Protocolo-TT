package com.example.in_help.ui;

class DatosIdVehiculo_F_IUPPA {
    Integer id_vehiculo;

    @Override
    public String toString() {
        return "DatosIdVehiculo_F_IUPPA{" +
                "id_vehiculo=" + id_vehiculo +
                '}';
    }

    public DatosIdVehiculo_F_IUPPA(Integer id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public Integer getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(Integer id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }
}
