package com.example.in_help.ui;

public class DatosActualiza_IUGN6 {

    Integer  id_estado;
    Integer  id_configuracion;

    @Override
    public String toString() {
        return "DatosActualiza_IUGN6{" +
                "id_estado=" + id_estado +
                ", id_configuracion=" + id_configuracion +
                '}';
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_configuracion() {
        return id_configuracion;
    }

    public void setId_configuracion(Integer id_configuracion) {
        this.id_configuracion = id_configuracion;
    }

    public DatosActualiza_IUGN6() {
        this.id_estado = id_estado;
        this.id_configuracion = id_configuracion;
    }

}
