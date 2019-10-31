package com.example.in_help.ui;

public class DatosVehiculo_F_IUPPA {
    Integer id_vehiculo;
    Integer id_usuario;
    String nu_placas;

    @Override
    public String toString() {
        return "DatosVehiculo_F_IUPPA{" +
                "id_vehiculo=" + id_vehiculo +
                ", id_usuario=" + id_usuario +
                ", nu_placas=" + nu_placas +
                '}';
    }

    public DatosVehiculo_F_IUPPA() {
        this.id_vehiculo = id_vehiculo;
        this.id_usuario = id_usuario;
        this.nu_placas = nu_placas;
    }

    public Integer getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(Integer id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNu_placas() {
        return nu_placas;
    }

    public void setNu_placas(String nu_placas) {
        this.nu_placas = nu_placas;
    }
}
