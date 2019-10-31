package com.example.in_help.ui;

class IntermediaVehiculosRequest {
    public IntermediaVehiculosRequest() {
        this.id_vehiculo_seguro = id_vehiculo_seguro;
        this.id_seguro = id_seguro;
        this.id_vehiculo = id_vehiculo;
    }

    public Integer getId_vehiculo_seguro() {
        return id_vehiculo_seguro;
    }

    public void setId_vehiculo_seguro(Integer id_vehiculo_seguro) {
        this.id_vehiculo_seguro = id_vehiculo_seguro;
    }

    public Integer getId_seguro() {
        return id_seguro;
    }

    public void setId_seguro(Integer id_seguro) {
        this.id_seguro = id_seguro;
    }

    public Integer getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(Integer id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    @Override
    public String toString() {
        return "IntermediaVehiculosRequest{" +
                "id_vehiculo_seguro=" + id_vehiculo_seguro +
                ", id_seguro=" + id_seguro +
                ", id_vehiculo=" + id_vehiculo +
                '}';
    }

    Integer id_vehiculo_seguro;
    Integer id_seguro;
    Integer id_vehiculo;
}
