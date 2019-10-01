package com.example.in_help.ui;

import java.util.Date;

public class CuentaRequest {

    public CuentaRequest(Integer id_cuenta, Integer id_usuario, Integer id_perfil, String fh_inicio, String fh_termino) {
        this.id_cuenta = id_cuenta;
        this.id_usuario = id_usuario;
        this.id_perfil = id_perfil;
        this.fh_inicio = fh_inicio;
        this.fh_termino = fh_termino;
    }

    @Override
    public String toString() {
        return "CuentaRequest{" +
                "id_cuenta=" + id_cuenta +
                ", id_usuario=" + id_usuario +
                ", id_perfil=" + id_perfil +
                ", fh_inicio=" + fh_inicio +
                ", fh_termino=" + fh_termino +
                '}';
    }

    Integer id_cuenta;
    Integer id_usuario;
    Integer id_perfil;
    String fh_inicio;
    String fh_termino;
}
