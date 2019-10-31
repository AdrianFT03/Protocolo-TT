package com.example.in_help.ui;

class DatosVehiculo_IUGN5 {

    Integer id_vehiculo;
    String nu_placas;

    @Override
    public String toString() {
        return "DatosVehiculo_IUGN5{" +
                "id_vehiculo=" + id_vehiculo +
                ", nu_placas=" + nu_placas +
                '}';
    }

    public DatosVehiculo_IUGN5() {
        this.id_vehiculo = id_vehiculo;
        this.nu_placas = nu_placas;
    }

    public Integer getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(Integer id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public String getNu_placas() {
        return nu_placas;
    }

    public void setNu_placas(String nu_placas) {
        this.nu_placas = nu_placas;
    }


}
