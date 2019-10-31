package com.example.in_help.ui;

public class DatosNotificacion_IUGN1 {


    Integer id_notifiacion;
    Integer id_tipo;
    String nu_placas;
    String fh_notificacion;
    Float latitud;
    Float longitud;

    public DatosNotificacion_IUGN1() {
        this.id_notifiacion = id_notifiacion;
        this.id_tipo = id_tipo;
        this.nu_placas = nu_placas;
        this.fh_notificacion = fh_notificacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Integer getId_notifiacion() {
        return id_notifiacion;
    }

    public void setId_notifiacion(Integer id_notifiacion) {
        this.id_notifiacion = id_notifiacion;
    }

    public Integer getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getNu_placas() {
        return nu_placas;
    }

    public void setNu_placas(String nu_placas) {
        this.nu_placas = nu_placas;
    }

    public String getFh_notificacion() {
        return fh_notificacion;
    }

    public void setFh_notificacion(String fh_notificacion) {
        this.fh_notificacion = fh_notificacion;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }



    @Override
    public String toString() {
        return "DatosNotificacion_IUGN1{" +
                "id_notifiacion=" + id_notifiacion +
                ", id_tipo=" + id_tipo +
                ", nu_placas=" + nu_placas +
                ", fh_notificacion=" + fh_notificacion +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }


}
