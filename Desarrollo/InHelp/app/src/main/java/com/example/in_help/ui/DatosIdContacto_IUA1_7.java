package com.example.in_help.ui;

public class DatosIdContacto_IUA1_7 {

    Integer id_contacto;
    Integer id_tipo;

    @Override
    public String toString() {
        return "DatosIdContacto_IUA1_7{" +
                "id_contacto=" + id_contacto +
                ", id_tipo=" + id_tipo +
                '}';
    }

    public DatosIdContacto_IUA1_7() {
        this.id_contacto = id_contacto;
        this.id_tipo = id_tipo;
    }

    public Integer getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(Integer id_contacto) {
        this.id_contacto = id_contacto;
    }

    public Integer getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
    }
}
