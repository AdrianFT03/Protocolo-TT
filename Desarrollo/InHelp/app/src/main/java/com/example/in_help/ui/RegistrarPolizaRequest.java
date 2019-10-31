package com.example.in_help.ui;

class RegistrarPolizaRequest {


    public RegistrarPolizaRequest(String nu_poliza, String fh_vigencia) {
        this.nu_poliza = nu_poliza;
        this.fh_vigencia = fh_vigencia;
    }

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

    @Override
    public String toString() {
        return "RegistrarPolizaRequest{" +
                "nu_poliza='" + nu_poliza +
                ", fh_vigencia='" + fh_vigencia +
                '}';
    }

    String nu_poliza;
    String fh_vigencia;
}
