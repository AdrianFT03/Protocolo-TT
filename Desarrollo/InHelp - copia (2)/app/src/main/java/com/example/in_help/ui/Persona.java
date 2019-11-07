package com.example.in_help.ui;

import java.util.Date;

public class Persona {
    public Integer getId_sexo() {
        return id_sexo;
    }

    public void setId_sexo(Integer id_sexo) {
        this.id_sexo = id_sexo;
    }

    public Integer getId_tipo_sangre() {
        return id_tipo_sangre;
    }

    public void setId_tipo_sangre(Integer id_tipo_sangre) {
        this.id_tipo_sangre = id_tipo_sangre;
    }

    public String getTx_nombre() {
        return tx_nombre;
    }

    public void setTx_nombre(String tx_nombre) {
        this.tx_nombre = tx_nombre;
    }

    public String getTx_perimer_ap() {
        return tx_perimer_ap;
    }

    public void setTx_perimer_ap(String tx_perimer_ap) {
        this.tx_perimer_ap = tx_perimer_ap;
    }

    public String getTx_seg_ap() {
        return tx_seg_ap;
    }

    public void setTx_seg_ap(String tx_seg_ap) {
        this.tx_seg_ap = tx_seg_ap;
    }

    public String getFh_nacimiento() {
        return fh_nacimiento;
    }

    public void setFh_nacimiento(String fh_nacimiento) {
        this.fh_nacimiento = fh_nacimiento;
    }

    Integer id_sexo;
    Integer id_tipo_sangre;
    String tx_nombre;
    String tx_perimer_ap;
    String tx_seg_ap;
    String fh_nacimiento;
}
