package com.example.in_help.ui;

class DatosCuentaRequest {
    public DatosCuentaRequest() {
        this.tx_login = tx_login;
        this.tx_password = tx_password;
    }

    public String getTx_login() {
        return tx_login;
    }

    public void setTx_login(String tx_login) {
        this.tx_login = tx_login;
    }

    public String getTx_password() {
        return tx_password;
    }

    public void setTx_password(String tx_password) {
        this.tx_password = tx_password;
    }

    @Override
    public String toString() {
        return "DatosCuentaRequest{" +
                "tx_login='" + tx_login +
                ", tx_password='" + tx_password +
                '}';
    }

    String tx_login , tx_password;
}
