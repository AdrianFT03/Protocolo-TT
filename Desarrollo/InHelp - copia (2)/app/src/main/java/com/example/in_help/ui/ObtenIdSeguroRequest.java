package com.example.in_help.ui;

class ObtenIdSeguroRequest {

    public ObtenIdSeguroRequest() {
        this.id_seguro = id_seguro;
        this.nu_poliza = nu_poliza;
        this.fh_vigencia = fh_vigencia;
    }

    public Integer getId_seguro() {
        return id_seguro;
    }

    public void setId_seguro(Integer id_seguro) {
        this.id_seguro = id_seguro;
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
        return "ObtenIdSeguroRequest{" +
                "id_seguro=" + id_seguro +
                ", nu_poliza='" + nu_poliza +
                ", fh_vigencia='" + fh_vigencia +
                '}';
    }

    Integer id_seguro;
    String nu_poliza;
    String fh_vigencia;

}
