package com.example.in_help.ui;

class DatosPersona_F_IUPP1A {

    Integer id_sexo;
    Integer id_tipo_sangre;
    String tx_id;
    String tx_nombre;
    String tx_primer_ap;
    String tx_segundo_ap;
    String fh_nacimiento;
    String tx_nombreE;

    @Override
    public String toString() {
        return "DatosPersona_F_IUPP1A{" +
                "id_sexo=" + id_sexo +
                ", id_tipo_sangre=" + id_tipo_sangre +
                ", tx_id='" + tx_id +
                ", tx_nombre='" + tx_nombre +
                ", tx_primer_ap='" + tx_primer_ap +
                ", tx_segundo_ap='" + tx_segundo_ap +
                ", fh_nacimiento='" + fh_nacimiento +
                ", tx_nombreE='" + tx_nombreE +
                '}';
    }

    public DatosPersona_F_IUPP1A(Integer id_sexo, Integer id_tipo_sangre, String tx_id, String tx_nombre, String tx_primer_ap, String tx_segundo_ap, String fh_nacimiento, String tx_nombreE) {
        this.id_sexo = id_sexo;
        this.id_tipo_sangre = id_tipo_sangre;
        this.tx_id = tx_id;
        this.tx_nombre = tx_nombre;
        this.tx_primer_ap = tx_primer_ap;
        this.tx_segundo_ap = tx_segundo_ap;
        this.fh_nacimiento = fh_nacimiento;
        this.tx_nombreE = tx_nombreE;
    }

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

    public String getTx_id() {
        return tx_id;
    }

    public void setTx_id(String tx_id) {
        this.tx_id = tx_id;
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

    public String getTx_segundo_ap() {
        return tx_segundo_ap;
    }

    public void setTx_segundo_ap(String tx_segundo_ap) {
        this.tx_segundo_ap = tx_segundo_ap;
    }

    public String getFh_nacimiento() {
        return fh_nacimiento;
    }

    public void setFh_nacimiento(String fh_nacimiento) {
        this.fh_nacimiento = fh_nacimiento;
    }

    public String getTx_nombreE() {
        return tx_nombreE;
    }

    public void setTx_nombreE(String tx_nombreE) {
        this.tx_nombreE = tx_nombreE;
    }
}
