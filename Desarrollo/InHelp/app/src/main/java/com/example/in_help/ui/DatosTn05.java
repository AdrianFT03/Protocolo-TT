package com.example.in_help.ui;

class DatosTn05 {
    Integer id_configuracion;
    Integer id_estado;
    Integer id_permiso;

    @Override
    public String toString() {
        return "DatosTn05{" +
                "id_configuracion=" + id_configuracion +
                ", id_estado=" + id_estado +
                ", id_permiso=" + id_permiso +
                '}';
    }

    public DatosTn05(Integer id_configuración, Integer id_estado, Integer id_permiso) {
        this.id_configuracion = id_configuración;
        this.id_estado = id_estado;
        this.id_permiso = id_permiso;
    }

    public Integer getId_configuración() {
        return id_configuracion;
    }

    public void setId_configuración(Integer id_configuración) {
        this.id_configuracion = id_configuración;
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
