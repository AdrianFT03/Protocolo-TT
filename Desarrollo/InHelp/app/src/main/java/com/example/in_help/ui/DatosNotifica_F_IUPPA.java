package com.example.in_help.ui;

public class DatosNotifica_F_IUPPA {

    Integer id_usuario;
    Integer id_tipo;
    Integer id_vehiculo;
    Integer id_bitacora;
    String fh_notificacion;
    Double latitud;
    Double longitud;

    @Override
    public String toString() {
        return "DatosNotifica_F_IUPPA{" +
                "id_usuario=" + id_usuario +
                ", id_tipo=" + id_tipo +
                ", id_vehiculo=" + id_vehiculo +
                ", id_bitacora=" + id_bitacora +
                ", fh_notificacion=" + fh_notificacion +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }

    public DatosNotifica_F_IUPPA(Integer id_usuario, Integer id_tipo, Integer id_vehiculo, Integer id_bitacora, String fh_notificacion, Double latitud, Double longitud) {
        this.id_usuario = id_usuario;
        this.id_tipo = id_tipo;
        this.id_vehiculo = id_vehiculo;
        this.id_bitacora = id_bitacora;
        this.fh_notificacion = fh_notificacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
    }

    public Integer getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(Integer id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public Integer getId_bitacora() {
        return id_bitacora;
    }

    public void setId_bitacora(Integer id_bitacora) {
        this.id_bitacora = id_bitacora;
    }

    public String getFh_notificacion() {
        return fh_notificacion;
    }

    public void setFh_notificacion(String fh_notificacion) {
        this.fh_notificacion = fh_notificacion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
