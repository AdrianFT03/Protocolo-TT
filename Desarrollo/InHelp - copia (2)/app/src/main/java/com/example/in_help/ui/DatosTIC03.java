package com.example.in_help.ui;

class DatosTIC03 {
    Integer id_contacto;
    Integer id_tipo;
    Integer id_usuario;
    Integer id_vehiculo;

    @Override
    public String toString() {
        return  "id_contacto=" + id_contacto +
                ", id_tipo=" + id_tipo +
                ", id_usuario=" + id_usuario +
                ", id_vehiculo=" + id_vehiculo ;
    }

    public DatosTIC03(Integer id_contacto, Integer id_tipo, Integer id_usuario, Integer id_vehiculo) {
        this.id_contacto = id_contacto;
        this.id_tipo = id_tipo;
        this.id_usuario = id_usuario;
        this.id_vehiculo = id_vehiculo;
    }
}
