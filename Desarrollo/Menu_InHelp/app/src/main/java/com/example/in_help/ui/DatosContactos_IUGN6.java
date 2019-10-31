package com.example.in_help.ui;

public class DatosContactos_IUGN6 {



    Integer id_configuracion;
    Integer id_estado;
    Integer id_tipo;
    String tx_nombre;
    String tx_primer_ap;
    String tx_seg_ap;

    @Override
    public String toString() {
        return "DatosContactos_IUGN6{" +
                "id_configuracion=" + id_configuracion +
                ", id_estado=" + id_estado +
                ", id_tipo=" + id_tipo +
                ", tx_nombre=" + tx_nombre +
                ", tx_primer_ap=" + tx_primer_ap +
                ", tx_seg_ap=" + tx_seg_ap +
                '}';
    }

    public DatosContactos_IUGN6() {
        this.id_configuracion = id_configuracion;
        this.id_estado = id_estado;
        this.id_tipo = id_tipo;
        this.tx_nombre = tx_nombre;
        this.tx_primer_ap = tx_primer_ap;
        this.tx_seg_ap = tx_seg_ap;
    }

    public Integer getId_configuracion() {
        return id_configuracion;
    }

    public void setId_configuracion(Integer id_configuracion) {
        this.id_configuracion = id_configuracion;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
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
}
