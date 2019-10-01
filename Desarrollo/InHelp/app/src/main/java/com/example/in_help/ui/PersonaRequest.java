package com.example.in_help.ui;

class PersonaRequest {

    public PersonaRequest() {
        this.tx_nombre = tx_nombre;
        this.tx_primer_ap = tx_primer_ap;
        this.tx_seg_ap = tx_seg_ap;
        this.fh_nacimiento = fh_nacimiento;
        this.id_sexo = id_sexo;
    }

    @Override
    public String toString() {
        return "PersonaRequest{" +
                "tx_nombre='" + tx_nombre +
                ", tx_primer_ap='" + tx_primer_ap +
                ", tx_seg_ap='" + tx_seg_ap +
                ", fh_nacimiento='" + fh_nacimiento+
                ", id_sexo=" + id_sexo +
                '}';
    }

    public String getTx_nombre() {
        return tx_nombre;
    }

    public void setTx_nombre(String tx_nombre) {
        this.tx_nombre = tx_nombre;
    }

    public String getTx_primer_ap() {
        return tx_primer_ap;
    }

    public void setTx_primer_ap(String tx_primer_ap) {
        this.tx_primer_ap = tx_primer_ap;
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

    public Integer getId_sexo() {
        return id_sexo;
    }

    public void setId_sexo(Integer id_sexo) {
        this.id_sexo = id_sexo;
    }

    String tx_nombre;
    String tx_primer_ap;
    String tx_seg_ap;
    String fh_nacimiento;
    Integer id_sexo;

}
