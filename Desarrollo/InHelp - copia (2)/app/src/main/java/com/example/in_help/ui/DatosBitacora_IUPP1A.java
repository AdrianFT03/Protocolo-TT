package com.example.in_help.ui;

class DatosBitacora_IUPP1A {
    Integer id_usuario;
    Integer id_vehiculo;

    @Override
    public String toString() {
        return "DatosBitacora_IUPP1A{" +
                "id_usuario=" + id_usuario +
                ", id_vehiculo=" + id_vehiculo +
                '}';
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

    public DatosBitacora_IUPP1A(Integer id_usuario, Integer id_vehiculo) {
        this.id_usuario = id_usuario;
        this.id_vehiculo = id_vehiculo;
    }
}
