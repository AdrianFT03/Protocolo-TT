package com.example.in_help.ui;

import java.io.Serializable;

public class Datos_IUGC1 implements Serializable {
    private Integer id_contacto;
    private Integer id_sexo;
    private Integer id_parentesco;
    private Integer id_tipo;
    private Integer id_usuario;
    private String tx_nombre;
    private String tx_primer_ap;
    private String tx_seg_ap;
    private String nu_tel;
    private String nu_poliza;
    private String fh_vigencia;
    private int Imagen1;
    private int Imagen2;
    private int Imagen3;
    private String nombreCompet;

    public Integer getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(Integer id_contacto) {
        this.id_contacto = id_contacto;
    }

    public Integer getId_sexo() {
        return id_sexo;
    }

    public void setId_sexo(Integer id_sexo) {
        this.id_sexo = id_sexo;
    }

    public Integer getId_parentesco() {
        return id_parentesco;
    }

    public void setId_parentesco(Integer id_parentesco) {
        this.id_parentesco = id_parentesco;
    }

    public Integer getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
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

    public String getNu_tel() {
        return nu_tel;
    }

    public void setNu_tel(String nu_tel) {
        this.nu_tel = nu_tel;
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

    public int getImagen1() {
        return Imagen1;
    }

    public void setImagen1(int imagen1) {
        Imagen1 = imagen1;
    }

    public int getImagen2() {
        return Imagen2;
    }

    public void setImagen2(int imagen2) {
        Imagen2 = imagen2;
    }

    public int getImagen3() {
        return Imagen3;
    }

    public void setImagen3(int imagen3) {
        Imagen3 = imagen3;
    }

    public String getNombreCompet() {
        return nombreCompet;
    }

    public void setNombreCompet(String nombreCompet) {
        this.nombreCompet = nombreCompet;
    }

    public Datos_IUGC1(Integer id_contacto, Integer id_sexo, Integer id_parentesco, Integer id_tipo, Integer id_usuario, String tx_nombre, String tx_primer_ap, String tx_seg_ap, String nu_tel, String nu_poliza, String fh_vigencia, int imagen1, int imagen2, int imagen3, String nombreCompet) {
        this.id_contacto = id_contacto;
        this.id_sexo = id_sexo;
        this.id_parentesco = id_parentesco;
        this.id_tipo = id_tipo;
        this.id_usuario = id_usuario;
        this.tx_nombre = tx_nombre;
        this.tx_primer_ap = tx_primer_ap;
        this.tx_seg_ap = tx_seg_ap;
        this.nu_tel = nu_tel;
        this.nu_poliza = nu_poliza;
        this.fh_vigencia = fh_vigencia;
        Imagen1 = imagen1;
        Imagen2 = imagen2;
        Imagen3 = imagen3;
        this.nombreCompet = nombreCompet;
    }
}
