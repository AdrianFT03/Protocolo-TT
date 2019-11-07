package com.example.in_help.ui;

import java.io.Serializable;

public class UpdatePoliza implements Serializable {

    public UpdatePoliza() {
        this.nu_poliza = nu_poliza;
        this.fh_vigencia = fh_vigencia;
        this.id_seguro = id_seguro;
    }

    @Override
    public String toString() {
        return "UpdatePoliza{" +
                "nu_poliza='" + nu_poliza +
                ", fh_vigencia='" + fh_vigencia +
                ", id_seguro=" + id_seguro +
                '}';
    }

    String nu_poliza;
    String fh_vigencia;
    Integer id_seguro;

    public String getNu_poliza() {
        return nu_poliza;
    }

    public void setNu_poliza(String nu_poliza) {
        this.nu_poliza = nu_poliza;
    }

    public String getFh_vigencia() {
        return fh_vigencia;
    }

    public void setFh_vigencia(String fh_vigencia) {
        this.fh_vigencia = fh_vigencia;
    }

    public Integer getId_seguro() {
        return id_seguro;
    }

    public void setId_seguro(Integer id_seguro) {
        this.id_seguro = id_seguro;
    }
}
