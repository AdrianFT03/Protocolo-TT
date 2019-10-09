package com.example.in_help.ui;

import java.io.Serializable;

class DatosPermiso_IGN6A implements Serializable {

    public DatosPermiso_IGN6A(Integer id_estado, Integer id_permiso) {
        this.id_estado = id_estado;
        this.id_permiso = id_permiso;
    }

    Integer id_estado;
    Integer id_permiso;

    @Override
    public String toString() {
        return "DatosPermiso_IGN6A{" +
                "id_estado=" + id_estado +
                ", id_permiso=" + id_permiso +
                '}';
    }

    public DatosPermiso_IGN6A() {
        this.id_estado = id_estado;
        this.id_permiso = id_permiso;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(Integer id_permiso) {
        this.id_permiso = id_permiso;
    }
}
