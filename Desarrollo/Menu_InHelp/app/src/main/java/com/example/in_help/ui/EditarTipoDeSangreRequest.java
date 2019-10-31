package com.example.in_help.ui;

public class EditarTipoDeSangreRequest {
    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    public Integer getId_tipo_sangre() {
        return id_tipo_sangre;
    }

    public void setId_tipo_sangre(Integer id_tipo_sangre) {
        this.id_tipo_sangre = id_tipo_sangre;
    }

    public EditarTipoDeSangreRequest() {
        this.id_persona = id_persona;
        this.id_tipo_sangre = id_tipo_sangre;
    }

    @Override
    public String toString() {
        return "EditarTipoDeSangreRequest{" +
                "id_persona=" + id_persona +
                ", id_tipo_sangre=" + id_tipo_sangre +
                '}';
    }

    Integer id_persona , id_tipo_sangre;
}
