package com.example.in_help.ui;

public class CrearVehiculoRequest {

    public CrearVehiculoRequest(Integer id_vehiculo, Integer id_usuario, String nu_placas, String tx_nombre, String color) {
        this.id_usuario = id_usuario;
        this.id_vehiculo = id_vehiculo;
        this.nu_placas = nu_placas;
        this.tx_nombre = tx_nombre;
        this.color = color;
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

    public String getNu_placas() {
        return nu_placas;
    }

    public void setNu_placas(String nu_placas) {
        this.nu_placas = nu_placas;
    }

    public String getTx_nombre() {
        return tx_nombre;
    }

    public void setTx_nombre(String tx_nombre) {
        this.tx_nombre = tx_nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "CrearVehiculoRequest{" +
                "id_usuario=" + id_usuario +
                ", id_vehiculo=" + id_vehiculo +
                ", nu_placas='" + nu_placas +
                ", tx_nombre='" + tx_nombre +
                ", color='" + color + '\'' +
                '}';
    }

    Integer id_usuario,id_vehiculo;
   String nu_placas,tx_nombre,color;


}
