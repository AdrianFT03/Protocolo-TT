package com.example.in_help.ui;

class EnfermedadCronicaRequest {
    public EnfermedadCronicaRequest() {
        this.tx_nombre = tx_nombre;
        this.id_persona = id_persona;
    }

    public String getTx_nombre() {
        return tx_nombre;
    }

    public void setTx_nombre(String tx_nombre) {
        this.tx_nombre = tx_nombre;
    }

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    @Override
    public String toString() {
        return "EnfermedadCronicaRequest{" +
                "tx_nombre='" + tx_nombre +
                ", id_persona=" + id_persona +
                '}';
    }

    String tx_nombre;
    Integer id_persona;
}
