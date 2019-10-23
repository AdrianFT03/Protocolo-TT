package com.example.in_help.ui;

public class DatosPoliza_IUGV1 {
    public DatosPoliza_IUGV1() {
        this.id_usuario = id_usuario;
        this.id_vehiculo = id_vehiculo;
        this.nu_poliza = nu_poliza;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(Integer id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public String getNu_poliza() {
        return nu_poliza;
    }

    public void setNu_poliza(String nu_poliza) {
        this.nu_poliza = nu_poliza;
    }

    @Override
    public String toString() {
        return "DatosPoliza_IUGV1{" +
                "id_usuario=" + id_usuario +
                ", id_vehiculo=" + id_vehiculo +
                ", nu_poliza='" + nu_poliza +
                '}';
    }

    Integer id_usuario;
    Integer id_vehiculo;
    String nu_poliza;

}
