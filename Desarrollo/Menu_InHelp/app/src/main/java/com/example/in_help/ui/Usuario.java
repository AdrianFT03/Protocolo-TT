package com.example.in_help.ui;

public class Usuario {
    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
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

    public String getNu_celular() {
        return nu_celular;
    }

    public void setNu_celular(String nu_celular) {
        this.nu_celular = nu_celular;
    }

    Integer id_usuario;
    Integer id_persona;
    String tx_login;
    String tx_password;
    String nu_celular;


}
