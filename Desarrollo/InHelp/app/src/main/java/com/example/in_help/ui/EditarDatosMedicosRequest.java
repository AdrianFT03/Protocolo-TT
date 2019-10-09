package com.example.in_help.ui;

class EditarDatosMedicosRequest {
    public EditarDatosMedicosRequest() {
        this.tx_id = tx_id;
        this.id_persona = id_persona;
    }

    public String getTx_id() {
        return tx_id;
    }

    public void setTx_id(String tx_id) {
        this.tx_id = tx_id;
    }

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    @Override
    public String toString() {
        return "EditarDatosMedicosRequest{" +
                "tx_id='" + tx_id +
                ", id_persona=" + id_persona +
                '}';
    }

    String tx_id;
    Integer id_persona;

}
