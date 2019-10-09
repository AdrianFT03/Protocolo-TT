package com.example.in_help.ui;

class EnfermedadCronicaRequest {
    public EnfermedadCronicaRequest() {
        this.tx_nombre = tx_nombre;
    }

    String tx_nombre;

    @Override
    public String toString() {
        return "EnfermedadCronicaRequest{" +
                "tx_nombre='" + tx_nombre +
                '}';
    }

    public String getTx_nombre() {
        return tx_nombre;
    }

    public void setTx_nombre(String tx_nombre) {
        this.tx_nombre = tx_nombre;
    }
}
