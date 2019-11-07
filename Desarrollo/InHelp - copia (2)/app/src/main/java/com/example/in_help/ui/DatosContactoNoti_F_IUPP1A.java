package com.example.in_help.ui;

import java.io.Serializable;

class DatosContactoNoti_F_IUPP1A implements Serializable {

    Integer id_contacto;
    String nu_tel;
    Integer id_configuracion;

    @Override
    public String toString() {
        return "DatosContactoNoti_F_IUPP1A{" +
                "id_contacto=" + id_contacto +
                ", nu_tel=" + nu_tel +
                ", id_configuracion=" + id_configuracion +
                '}';
    }

    public DatosContactoNoti_F_IUPP1A() {
        this.id_contacto = id_contacto;
        this.nu_tel = nu_tel;
        this.id_configuracion = id_configuracion;
    }

    public Integer getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(Integer id_contacto) {
        this.id_contacto = id_contacto;
    }

    public String getNu_tel() {
        return nu_tel;
    }

    public void setNu_tel(String nu_tel) {
        this.nu_tel = nu_tel;
    }

    public Integer getId_configuracion() {
        return id_configuracion;
    }

    public void setId_configuracion(Integer id_configuracion) {
        this.id_configuracion = id_configuracion;
    }
}
