package com.example.in_help.ui;

import java.io.Serializable;

class DatosConPermiso_F_IUPP1A implements Serializable {

    Integer id_permiso_conf;
    Integer id_configuracion;
    Integer id_estado;
    Integer id_permiso;

    @Override
    public String toString() {
        return "DatosConPermiso_F_IUPP1A{" +
                "id_permiso_conf=" + id_permiso_conf +
                ", id_configuracion=" + id_configuracion +
                ", id_estado=" + id_estado +
                ", id_permiso=" + id_permiso +
                '}';
    }

    public DatosConPermiso_F_IUPP1A() {
        this.id_permiso_conf = id_permiso_conf;
        this.id_configuracion = id_configuracion;
        this.id_estado = id_estado;
        this.id_permiso = id_permiso;
    }

    public Integer getId_permiso_conf() {
        return id_permiso_conf;
    }

    public void setId_permiso_conf(Integer id_permiso_conf) {
        this.id_permiso_conf = id_permiso_conf;
    }

    public Integer getId_configuracion() {
        return id_configuracion;
    }

    public void setId_configuracion(Integer id_configuracion) {
        this.id_configuracion = id_configuracion;
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
