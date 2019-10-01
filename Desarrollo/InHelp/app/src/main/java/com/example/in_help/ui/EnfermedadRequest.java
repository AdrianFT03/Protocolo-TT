package com.example.in_help.ui;

public class EnfermedadRequest {
    @Override
    public String toString() {
        return "EnfermedadRequest{" +
                "id_enfermedad=" + id_enfermedad +
                ", id_persona=" + id_persona +
                ", id_tipo=" + id_tipo +
                ", tx_nombre=" + tx_nombre +
                '}';
    }




    public EnfermedadRequest(Integer id_enfermedad, Integer id_persona,Integer id_tipo, String tx_nombre) {
        this.id_enfermedad = id_enfermedad;
        this.id_persona = id_persona;
        this.id_tipo = id_tipo;
        this.tx_nombre = tx_nombre;
    }

    public Integer id_enfermedad;
    public Integer id_persona;
    public Integer id_tipo;
    public String tx_nombre;
}
