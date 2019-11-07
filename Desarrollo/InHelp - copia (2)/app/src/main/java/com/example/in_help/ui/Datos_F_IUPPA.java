package com.example.in_help.ui;

import java.io.Serializable;

public class Datos_F_IUPPA implements Serializable {
    String palcas;

    @Override
    public String toString() {
        return  palcas;
    }

    public Datos_F_IUPPA(String palcas) {
        this.palcas = palcas;
    }

    public String getPalcas() {
        return palcas;
    }

    public void setPalcas(String palcas) {
        this.palcas = palcas;
    }
}
