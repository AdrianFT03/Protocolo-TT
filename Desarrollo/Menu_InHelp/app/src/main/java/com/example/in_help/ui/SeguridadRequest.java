package com.example.in_help.ui;

public class SeguridadRequest {
    @Override
    public String toString() {
        return "SeguridadRequest{" +
                "id_seguridad=" + id_seguridad +
                "id_persona=" + id_persona +
                ", id_tipo_seguridad=" + id_tipo_seguridad +
                ", tx_id=" + tx_id +
                '}';
    }

    public SeguridadRequest(Integer id_seguridad,Integer id_persona, Integer id_tipo_seguridad, String tx_id) {
        this.id_seguridad = id_seguridad;
        this.id_persona = id_persona;
        this.id_tipo_seguridad = id_tipo_seguridad;
        this.tx_id = tx_id;
    }

    public Integer id_seguridad;
    public Integer id_persona;
    public Integer id_tipo_seguridad;
    public String tx_id;

}
